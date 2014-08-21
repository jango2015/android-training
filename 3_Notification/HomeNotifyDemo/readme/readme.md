## 功能实现

**功能一**  
App 在前台运行过程中，按下 Home 弹出 toast 提示：HomeNotifyDemo 进入后台运行

**功能二**   
同时，在 Notification 窗口显示：icon + HomeNotifyDemo

## 注意

### 图片被系统截断
图片过大时，notification area 将会被自动截断，推荐大小 24 * 24 dp

## 遗留问题

### icon 显示问题

1、当设置如下时

```java
  .setSmallIcon(R.drawable.ic_stat_notify_home_pressed)
```

icon 的情况如下：
- notification area，有
- notification drawer 左边，有
- notification drawer 右下角，无


2、当设置如下时

```java
  .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
  .setSmallIcon(R.drawable.ic_stat_notify_home_pressed)
```

icon 的情况如下：
- notification area，有，显示 ic_stat_notify_home_pressed
- notification drawer 左边，有，显示 ic_launcher
- notification drawer 右下角，有，显示 ic_stat_notify_home_pressed

也就是说如下效果无法实现：
- notification area，有，显示 ic_stat_notify_home_pressed
- notification drawer 左边，有，显示 ic_launcher
- notification drawer 右下角，`无`
