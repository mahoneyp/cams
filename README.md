## Cams

![Cams](https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/5_cover.jpg)

A simple mobile application for Android for playing RTSP streams from IP cameras.

Peculiarities:

- View RTSP streams from any IP cameras, including H.265+.
- View multiple streams simultaneously.
- Twenty times image magnification.
- Support for dual-channel cameras.
- View videos or images via SFTP protocol.
- Possibility to configure notifications about camera motion detector activation.
- High connection speed.
- Extremely easy navigation and control.
- Maximum security and data confidentiality.
- Switching TCP/UDP protocol.
  This option is important when viewing cameras over the Internet, where UDP may not be supported or work poorly.

<img src="https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/1_main_ru.jpg"
alt="Main screen"
width="200">&nbsp;
<img src="https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/2_edit_ru.jpg"
alt="Edit screen"
width="200">&nbsp;
<img src="https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/3_files_ru.jpg"
alt="Files screen"
width="200">&nbsp;
<img src="https://raw.githubusercontent.com/vladpen/cams/main/fastlane/metadata/android/ru-RU/images/phoneScreenshots/4_video_ru.jpg"
alt="Video screen"
width="200">

The application is written for use in conjunction with a server. [python-rtsp-server](https://github.com/vladpen/python-rtsp-server),
but works perfectly autonomously thanks to the ability to connect to any IP cameras, as well as video recorders that support SFTP.

Plays most types of video streams (not just RTSP).
The screenshot above shows an image from a real video camera and three test videos in Group mode.

*IMPORTANT: The app is focused on security and privacy, so it does not collect or process any user information.
No data is sent to any servers, including Google's technical infrastructure or camera manufacturer cloud storage.*

## Installation

You can build the APK file yourself or download it from Github.
([64 bits](https://github.com/vladpen/cams/raw/main/app/release/cams-arm64-v8a-release.apk),
[32 pages](https://github.com/vladpen/cams/raw/main/app/release/cams-armeabi-v7a-release.apk)),
install using [F-Droid](https://f-droid.org/ru/packages/com.vladpen.cams/) (64-bit platforms only)
or [RuStore](https://www.rustore.ru/catalog/app/com.vladpen.cams).
You can also install and update this application using [Obtainium](https://github.com/ImranR98/Obtainium).
In the folder [app/release](https://github.com/vladpen/cams/tree/main/app/release)
There are files for 64-bit (arm64-v8a) and 32-bit (armeabi-v7a) ARM architecture,
as well as x86-64 and x86.

## Setting

To connect to a video camera, enter its URL, as provided by the manufacturer, in the "Address" field. It usually looks like this:
```
[rtsp://][<пользователь>:<пароль>@]<IP>[:<порт>][/<путь>]
```
Parameters in square brackets are optional (depending on camera settings).

For dual-channel cameras, you can additionally specify the address of the second channel.
For example, for Hikvision cameras and their derivatives the path will look like this:
```
ISAPI/Streaming/Channels/<номер канала>
```
Then the first channel (high definition) will have the number 101, and the second (low definition) - 102.

Low resolution channels can be used to speed up image loading,
to save traffic and reduce the load on the device's processor.
This is especially convenient for viewing a group of cameras with a low connection speed. 
During playback, channels can be switched using the K1/K2 button in the lower right corner of the screen.
On camera group screens, K2 is used by default.

Also, to reduce the load, playback of cameras that extend beyond the screen boundaries when the image is enlarged is paused.

The address of the SFTP server or video recorder looks like this:
```
[sftp://]<пользователь>:<пароль>@<IP>[:<порт>][/<путь>]
```
ATTENTION! It is strongly recommended not to use administrator access data.
For the SFTP server, it is better to create a chroot, for example, as described [here](https://wiki.archlinux.org/title/SFTP_chroot).

**Tip:** You can use an emoji as an icon in your camera name.
For example, the screenshots above use icons from a standard mobile phone set.

## Motion alert

Optionally, the application can notify you when the camera's motion detector is triggered.
The alert is triggered when a new image from the camera appears in the specified SFTP server folder.
For this feature to work, you need to configure the cameras and the server that stores the received images.
These settings are described in detail in the parallel project. [Cams-PWA](https://github.com/vladpen/cams-pwa).

Detailed discussion of the application: [habr.com/ru/post/654915](https://habr.com/ru/post/654915/)
and servers: [habr.com/ru/post/597363](https://habr.com/ru/post/597363/).

[<img src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png"
alt="Get it on Github"
height="80">](https://github.com/vladpen/cams/tree/main/app/release)
[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png"
alt="Get it on F-Droid"
height="80">](https://f-droid.org/packages/com.vladpen.cams/)
[<img src="https://raw.githubusercontent.com/ImranR98/Obtainium/main/assets/graphics/icon_small.png"
alt="Obtainium" height="80" width="80">](https://github.com/ImranR98/Obtainium)

&nbsp; [<img src="https://user-images.githubusercontent.com/3853013/194689050-e6da2f21-9aa3-4662-9b7d-7293b140f22f.svg"
alt="Available in RuStore"
height="57">](https://apps.rustore.ru/app/com.vladpen.cams)

*Copyright (c) 2022-2025 vladpen under MIT license. Use it with absolutely no warranty.*
