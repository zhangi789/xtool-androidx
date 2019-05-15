# XTool
## 个人收藏的Android常用工具类 兼容AndroidX。其主要功能有：

### 1.   Android 6.0 权限封装
### 2.   上传图片 图库和拍照返回Uri
### 3.   Android倒计时
### 4.   File 工具类
### 5.   Log工具类
### 6.   Toast工具类
### 7.   Handler工具类
### 8.   SharedPreferences工具类
### 9.   加密工具类支持MD5和Base64
### 10.  基础工具类

# 集成AS 
> Step 1.先在 build.gradle(Project:XXXX) 的 repositories 添加:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
> Step 2. 然后在 build.gradle(Module:app) 的 dependencies 添加:

	dependencies {
	
	       //没有兼容AndroudX        AS 3.1.2
           implementation 'com.github.zhangi789:xtool-android:1.0'
          
           //兼容AndroudX  需要满足  AS 3.2  gradle 4.6以上
           implementation 'com.github.zhangi789:xtool-androidx:4.3.2'
	}
	
	> Step 3. 如果使用AndroidX，请在 gradle.properties 文件下添加:
	          如果没用 不要添加
              android.useAndroidX=true
              android.enableJetifier=true

## 基础功能
##### 
```java

//参考类
XTool        基类


```



### Android 6.0 权限封装
#### 权限检测
#### XTool.checkPermission(Activity context, String[] perms);

#### 权限请求
####  XTool.requestPermission(Activity context, String tip, int requestCode, String[] perms);
#### 实际使用


To begin using EasyPermissions, have your `Activity` (or `Fragment`) override the `onRequestPermissionsResult` method,implement the `PermissionCallbacks` interface:

```java
public class PermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perssion);

        boolean b = XTool.checkPermission(this, perms);
        if (b == false) {
            XTool.requestPermission(this, "我需要你！", 90, perms);
        } else {
            Toast.makeText(this, "已经请求过了", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将请求结果传递EasyPermission库处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //当从软件设置界面，返回当前程序时候
            case AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE:
                break;
            default:
                break;
        }
    }
}


```



### 上传图片    图库和拍照返回Uri
#### 温馨提示，使用前请请求权限     
#### 拍照
####   XTool.openCamera(Activity context, int requestCode, String picturePath);
#### 图库
####   XTool.openGallery(Activity context, int requestCode);


To begin using , have your `Activity` (or `Fragment`) override the `onActivityResult` method:


```java
//第一步
String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

// 创建File 对象
File mFlie=XTool.getFlie();

//第三步 拍照点击事件
XTool.openCamera(this, 1000, XTool.getPicturePath(mFlie));

 //第三步 图库点击事件
XTool.openGallery(this, 1500);

//第四步 重写onActivityResult  即可 返回值URi  剩下自己处理 

 @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
        
            case 1000:
                Uri uri = Uri.fromFile(mFlie);
                Log.i("GGG","拍照 "+uri.toString());
                Log.i("GGG","拍照1 "+XTool.getRealFilePathFromUri(this,uri));


                //剩下自己处理   裁剪。。。。。
                break;
            case 1500:
                Uri uri2 = intent.getData();
                Log.i("GGG","图库 "+uri2.toString());
                Log.i("GGG","图库1 "+XTool.getRealFilePathFromUri(this,uri2));
                //剩下自己处理   裁剪。。。。。
                break;
            default:
                break;
        }
    }





```

### Android倒计时   
#### 支持开始倒计时、暂停倒计时、继续倒计时、停止倒计时、重新倒计时
####  调用
####   XTool.getCountDown(long millisInFuture, long countDownInterval);
####  实际使用
```java		
//总时长 间隔时间
private CountDownTimerSupport mTimer;
//总时长 间隔时间
  mTimer=XTool.getCountDown(1000,1000);
                countDownTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
                    @Override
                    public void onTick(long millisUntilFinished) {


                        Log.i("GGG","millisUntilFinished "+millisUntilFinished+" "+millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {
                        Log.i("GGG","onFinish");
                    }
                });
                
              
    //  启动倒计时   
         mTimer.start();
         
    //  暂停倒计时
         mTimer.pause();

    //  恢复倒计时
         mTimer.resume();

    // 停止倒计时
        mTimer.stop();

    // 重置并启动倒计时
        mTimer.reset();
        mTimer.start();
```
## File 工具类使用
#### 功能创建File 两种方式   内存File 和自定义File 
文件名默认 ：生成的文件名:日期，md5加密

```java	
//自定义获得文件夹存储文件
  路径：/storage/emulated/0/image/ab777ea0de482551002c45a3548edd86.png
File mFlie= XTool.getFlie();

//好处:提供的是私有的目录，在app卸载后会被删除
File mFile=XTool.getDiskFile(this);
```
```java	
//根据Flie 获得 picturePath

String picturePath=  XTool.getPicturePath(File mFile);
```
```java	
//根据Uri返回文件绝对路径  兼容了file:///开头的 和 content://开头的情况

String picturePath=  XTool.+XTool.getRealFilePathFromUri(Context context, Uri uri);
```



## Toast工具类
#### 居中Toast
XTool.showLongToast(Context context, String content);



XTool.showShortToast(Context context, String content);
```java	
     XTool.showLongToast(this,"info");
     
     XTool.showShortToast(this,"info");

```

## Log工具类
####   支持打印json   其他使用跟log一致
 XTool.json(String tag, String message, boolean isOutputOriginalContent);
```java	

     XTool.json("GGG","json",false);
     XTool.i("GGG","info");
     XTool.d("GGG","info");
     XTool.w("GGG","info");

```


## Handler工具类
#### Handler 支持普通和延迟
XTool.handleMsg(Handler mHandler, int what, Bundle data);
     
XTool.handleDelayedMsg(Handler mHandler, int what, long time, Bundle data);
```java
```




## SharedPreferences工具类
#### 使用方式一致 具体如下  支持

```java
        XTool.putString((Context context, String key, String value);
        XTool.getString(Context context, String key, String defValue)
        
        XTool.putInt(Context context, String key, int value);
        XTool.getInt(Context context, String key, int defValue)
        
        XTool.putBoolean(Context context, String key, boolean value);
        XTool.getBoolean(Context context, String key, boolean defValue);
        
        XTool.putFloat(Context context, String key, float value);
        XTool.getFloat(Context context, String key, float defValue)


```


## 加密工具类
#### 支持MD5   Base64 编码和解码

```java
    //Base64解码
     byte[] data =  XTool.base64Decode(String input)
     byte[] data =  XTool.base64Decode(byte[] input)
    
    //Base64编码
     byte[] data =  XTool.base64Encode(byte[] input);
     byte[] data =  XTool.base64Encode(String input);
     //Base64编码
     String data =  XTool.base64Encode2String(byte[] input)
     
     //MD5加密
     String data =  XTool.getMD5(String data);
     String data =  XTool.getMD5(byte[] data);
```

## 基础工具类
#### 功能 获得app相关信息 判断手机号，隐藏手机几位 
```java
      
      XTool.getAppVersionCode(Context context);
      XTool.getAppVersionName(Context context);
      
      XTool.isMobileNO(String mobiles);
      XTool.hideMobilePhone4(String mobile_phone);
      
      XTool.isNotEmpty(String result);
     
      //byte[]   转化为16进制字符串
      XTool.bytes2HexString(byte[] bytes)
      
      //16进制字符串   转化为byte[]
      XTool.hexString2Bytes(String hexString);
      
      //获得系统当前事件 format="yyyy-MM-dd HH:mm:ss"  需自定义
      XTool.getSysCurTime(String format);
```
