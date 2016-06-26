# DyncRTMPLiveClient-Android
基于RTMP协议的推流和拉流Demo，针对RTMP Player进行了进一步优化，拉流播放器能够实现秒开。Demo具有Eclipse版和Android Studio版

Android 直播（网络自适应码率RTMP publisher）、点播播放器（播放器经过专业优化，可实现秒开RTMP Player）
##简介
DyncLiveClient是为移动端应用量身打造的基于RTMP协议的流媒体直播系统。通过集成本SDK，只需几个简单API调用，便可实现一套完整的直播流媒体应用基础。包含了流媒体应用中：『采集->编码->传输->解码->播放』的所有步骤。
#SDK包含
RTMP 推流器</br>
RTMP 播放器</br>

##编译环境
**Eclipse** 和**Android Studio**

##支持的系统平台
**Android** 4.0及以上

##支持的CPU架构
**Android** armv7 arm64  

##支持的流媒体服务端
fms, wowza, evostream, red5, crtmpserver, nginx-rtmp-module, srs及其他标准RTMP协议服务端

##支持的流媒体云服务器
[奥点云](http://www.aodianyun.com/)、[七牛](http://www.qiniu.com/)及其他标准RTMP协议云服务器

##直播发布特性
* H.264/AAC 组合的RTMP协议音视频流发布
* 全屏视频采集，原画质缩放
* 集优化H.264软件编码器，性能强劲，兼容性极强
* 视频分辨率以及码率自选
* 回声消除，利用webrtc音频机制，不再有沙沙声
* 支持发布中途切换前后摄像头
* 支持发布中网络自适应，根据带宽大小来自动适应分辨率以及码率，让视频更顺畅
* 支持基于GPU加速的实时美颜滤镜


##直播播放特性
* 只为RTMP协议优化的码流解析器，极短的分析时间，秒开RTMP视频流
* 支持的视频解码器:H.264, FLV, VP6
* 支持的音频解码器:AAC, MP3, SPEEX, NELLYMOSER, ADPCM_SWF, G.711
* OpenGL ES视频渲染
* 支持主播停止推流后，播放端立即获取到结束状态（RTMP协议下）

##ipv6
该库已经适配

##商用授权
程序发布需商用授权，业务咨询请联系
QQ:809564859 </br>
QQ交流群:6837127</br>
联系电话:021-65650071</br>
Email:niuming@dync.cc</br>
##关于直播
本公司有一整套直播解决方案，特别针对移动端。本公司开发者平台[www.anyrtc.io](http://www.anyrtc.io)。除了基于RTMP协议的直播系统外，我公司还有基于WebRTC的时时交互直播系统、P2P呼叫系统、会议系统等。快捷集成SDK，便可让你的应用拥有时时通话功能。欢迎您的来电~
## License

DyncLiveClient is available under the MIT license. See the LICENSE file for more info.
