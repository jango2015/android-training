分以下两种情况分别做过测试

## 注释掉 activity

### 场景

在 AndroidManifest.xml 中注释掉 activity，即为：
```xml
<application
    android:allowBackup="true"
    android:icon="@drawable/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/AppTheme" >
    <receiver android:name="vincent4j.receiverstatictriggerdemo.NetworkStateReceiver" >
        <intent-filter>
            <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
        </intent-filter>
    </receiver>
</application>
```

### run 时的提示

<pre>
[2013-08-13 16:43:48 - ReceiverStaticTriggerDemo] ------------------------------
[2013-08-13 16:43:48 - ReceiverStaticTriggerDemo] Android Launch!
[2013-08-13 16:43:48 - ReceiverStaticTriggerDemo] adb is running normally.
[2013-08-13 16:43:48 - ReceiverStaticTriggerDemo] No Launcher activity found!
[2013-08-13 16:43:48 - ReceiverStaticTriggerDemo] The launch will only sync the application package on the device!
[2013-08-13 16:43:48 - ReceiverStaticTriggerDemo] Performing sync
[2013-08-13 16:43:49 - ReceiverStaticTriggerDemo] Automatic Target Mode: using existing emulator 'emulator-5554' running compatible AVD 'AVD-2.3.3'
[2013-08-13 16:43:49 - ReceiverStaticTriggerDemo] Uploading ReceiverStaticTriggerDemo.apk onto device 'emulator-5554'
[2013-08-13 16:43:52 - ReceiverStaticTriggerDemo] Installing ReceiverStaticTriggerDemo.apk...
[2013-08-13 16:43:56 - ReceiverStaticTriggerDemo] Success!
[2013-08-13 16:43:56 - ReceiverStaticTriggerDemo] \ReceiverStaticTriggerDemo\bin\ReceiverStaticTriggerDemo.apk installed on device
[2013-08-13 16:43:56 - ReceiverStaticTriggerDemo] Done!
</pre>

### 测试

- 2.3.3 模拟器     
一切正常，能打出相应日志。

- 4.3 Nexus 4    
无法打出日志，就是没有触发 receiver。

## 开启 activity

### 场景

在 AndroidManifest.xml 中开启 activity，即为：

```xml
<application
    android:allowBackup="true"
    android:icon="@drawable/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/AppTheme" >
    <activity
        android:name="vincent4j.receiverstatictriggerdemo.MainActivity"
        android:label="@string/app_name" >
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>

    <receiver android:name="vincent4j.receiverstatictriggerdemo.NetworkStateReceiver" >
        <intent-filter>
            <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
        </intent-filter>
    </receiver>
</application>
```

### 测试

以下都是在 Nexus 4 上做的测试

- 首先启动该 app，然后切换网络    
能打出日志，即正常。

- kill 掉该 app，然后切换网络    
在 DDMS 中看到，该 app 被启动，然后接着在 Logcat 中对应日志打出，即正常。

## 结论

AndroidManifest.xml 中没有配置 LAUNCHER，故无法触发。    
疑问：那为何 2.3.3 模拟器又是可以的呢？
