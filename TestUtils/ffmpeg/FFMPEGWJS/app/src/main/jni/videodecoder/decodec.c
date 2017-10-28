//
// Created by WJS on 2016/12/28.
//
#include "libavformat/avformat.h"
#include "libswscale/swscale.h"
#include "libavutil/imgutils.h"
#define LOGE printf
int decodec(char *input,char *output)
{
    AVFormatContext* pFormat = NULL;
    AVCodecContext* video_dec_ctx = NULL;
    AVCodec* video_dec = NULL;
    AVDictionary    *optionsDict = NULL;
    AVFrame *pFrame = NULL;
    struct SwsContext *sws_ctx = NULL;
    AVPacket        packet;
    int i, videoStream;
    int frameFinished;

    FILE *yuv_file = fopen(output, "ab");
    if (!yuv_file) {
        return 0;
    }
    LOGE("注册所有解码器");
    av_register_all();
    LOGE("打开文件");
    if (avformat_open_input(&pFormat, input, NULL, NULL) < 0)
    {
        return 0;
    }
    LOGE("查视屏信息");
    if (avformat_find_stream_info(pFormat, NULL) < 0)
    {
        return 0;
    }
    LOGE("打印视频流的信息");
    av_dump_format(pFormat, 0, input, 0);
    videoStream = -1;
    LOGE("查找视屏流");
    for (i = 0; i<pFormat->nb_streams; i++)
    {
        if (pFormat->streams[i]->codec->codec_type == AVMEDIA_TYPE_VIDEO)
        {
            videoStream = i;
            break;
        }
    }
    if (videoStream == -1)
        return -1;
    LOGE("获取视屏流解码环境");
    video_dec_ctx = pFormat->streams[videoStream]->codec;
    LOGE("获取视屏流解码器");
    video_dec = avcodec_find_decoder(video_dec_ctx->codec_id);
    if (video_dec == NULL) {
        return -1; // Codec not found
    }
    LOGE("装载视屏流");
    if (avcodec_open2(video_dec_ctx, video_dec, &optionsDict) < 0)  //解码视屏流
    {
        return 0;
    }
    LOGE("初始化视屏帧");
    pFrame = av_frame_alloc();
    LOGE("初始化缓存视屏帧");
    AVFrame* pFrameYUV = av_frame_alloc();
    if (pFrameYUV == NULL)
        return -1;
    LOGE("获取图像处理环境");
    sws_ctx =
            sws_getContext
                    (
                            video_dec_ctx->width,
                            video_dec_ctx->height,
                            video_dec_ctx->pix_fmt,
                            video_dec_ctx->width,
                            video_dec_ctx->height,
                            AV_PIX_FMT_YUV420P,
                            SWS_BILINEAR,
                            NULL,
                            NULL,
                            NULL
                    );
    LOGE("获取YUV420图像大小");
    int numBytes = av_image_get_buffer_size(AV_PIX_FMT_YUV420P,video_dec_ctx->width, video_dec_ctx->height,1);
    LOGE("开辟内存装载YUV420");
    uint8_t* buffer = (uint8_t *)av_malloc(numBytes*sizeof(uint8_t));
    LOGE("将开辟的内存和缓存视屏帧关联起来");
    avpicture_fill((AVPicture *)pFrameYUV, buffer, AV_PIX_FMT_YUV420P, video_dec_ctx->width, video_dec_ctx->height);
    LOGE("视屏帧关联成功");
    while (av_read_frame(pFormat, &packet) >= 0) {
        LOGE("读取一个屏帧");
// Is this a packet from the video stream?
        if (packet.stream_index == videoStream) {
            LOGE("判断这个视屏帧是否是视屏");
// Decode video frame
            LOGE("解码该视屏帧");
            avcodec_decode_video2(video_dec_ctx, pFrame, &frameFinished,
                                  &packet);
// Did we get a video frame?
            if (frameFinished) {
                LOGE("转换成YUV420屏帧");
                sws_scale
                        (
                                sws_ctx,
                                (uint8_t const *const *)pFrame->data,
                                pFrame->linesize,
                                0,
                                video_dec_ctx->height,
                                pFrameYUV->data,
                                pFrameYUV->linesize
                        );
                LOGE("YUV420屏帧写入文件");
                LOGE("width:%d---height:%d",video_dec_ctx->width, video_dec_ctx->height);
                fwrite(pFrameYUV->data[0], (video_dec_ctx->width)*(video_dec_ctx->height), 1, yuv_file);
                fwrite(pFrameYUV->data[1], (video_dec_ctx->width)*(video_dec_ctx->height) / 4, 1, yuv_file);
                fwrite(pFrameYUV->data[2], (video_dec_ctx->width)*(video_dec_ctx->height) / 4, 1, yuv_file);
            }
        }
        av_packet_unref(&packet);
    }
    LOGE("视屏解码完毕");
    fclose(yuv_file);
    av_free(pFrame);
    av_free(pFrameYUV);
    avcodec_close(video_dec_ctx);
    avformat_close_input(&pFormat);
    return 0;
}