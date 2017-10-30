package org.libsdl.app;
/**
 Simple nativeInit() runnable
 */
public class SDLMain implements Runnable {
    @Override
    public void run() {
        // Runs SDL_main()
        if(SDLActivity.playerURL!=null&&!SDLActivity.playerURL.trim().equalsIgnoreCase("")) {
            SDLActivity.nativeInit(SDLActivity.playerURL);
        }
        else
        {
            SDLActivity.nativeInit("http://ren.itingwang.com:8002/uplodfile/article/20160118/gas_safe_information.mp4");
        }
        //Log.v("SDL", "SDL thread terminated");
    }
}