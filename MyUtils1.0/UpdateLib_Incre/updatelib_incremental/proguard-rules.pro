#指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
 #优化  不优化输入的类文件
-dontoptimize
 #预校验
-dontpreverify
 #混淆时是否记录日志
-verbose
 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-dontwarn javax.annotation.**

#保留原文件名及行号
-keepattributes SourceFile,LineNumberTable

#混淆本地AAR文件
#-libraryjars libs/photo-cropper-library.aar

#保护注解
-keepattributes *Annotation*

#混淆保持
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep class javax.**
-keep class android.webkit.**
-keepattributes EnclosingMethod,Signature
-dontwarn android.net.SSLCertificateSocketFactory
-dontwarn android.app.Notification
-dontwarn java.lang.invoke.*
#如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.support.v7.widget.RecyclerView.Adapter
#忽略警告
-ignorewarning

#如果引用了v4或者v7包
-dontwarn android.support.**

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

#####################记录生成的日志数据,gradle build时在本项目根目录输出################
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt
#####################记录生成的日志数据，gradle build时 在本项目根目录输出-end############

#Butterknife混淆
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#Butterknife混淆

#友盟相关包
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView



-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable



-keep public class javax.**
-keep public class android.webkit.**


-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

#王家胜
#GSON
-keepattributes Exceptions,InnerClasses
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
#-keep class com.wenyan.liquefiedgas.bean.** { *; }

-keepclassmembers class * extends com.sea_monster.dao.AbstractDao {
 public static java.lang.String TABLENAME;
}
-keep class **$Properties
-dontwarn org.eclipse.jdt.annotation.**

-dontwarn javax.annotation.**



#百度地图
-keep class com.baidu.** { *; }
-keep interface com.baidu.** { *; }
-dontwarn com.baidu.**
-keep class vi.com.gdi.bgl.android.**{*;}


-keep class com.sinovoice.hcicloudsdk.**{*;}
-keep interface com.sinovoice.hcicloudsdk.**{*;}
-dontwarn com.sinovoice.hcicloudsdk.**



-keep class com.google.** { *; }
-keep interface com.google.** { *; }
-dontwarn com.google.**

-keep class com.wjs.** { *; }
-keep interface com.wjs.** { *; }
-dontwarn com.wjs.**

-keep class com.beust.** { *; }
-keep interface com.beust.** { *; }
-dontwarn com.beust.**

-keep class in.srain.** { *; }
-keep interface in.srain.** { *; }
-dontwarn in.srain.**

#-keep class com.wenyan.liquefiedgas.pull.** { *; }
#-keep interface com.wenyan.liquefiedgas.pull.** { *; }
#-dontwarn com.wenyan.liquefiedgas.pull.**

#[腾讯信鸽]
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.**  {* ;}
-keep class com.tencent.mid.**  {* ;}
#[/腾讯信鸽]

#Fresco混淆