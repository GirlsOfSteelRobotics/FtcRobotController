@echo off
echo Connecting to Moto Phone for wireless code upload...
echo.

rem Attempt to set phone's ADB connection to port 5555
rem Also attempt to connect to phone's static IP address
adb tcpip 5555
adb connect 192.168.49.1

echo.
echo.
echo ***********************************************************
echo * If there is no error above, the phones are connected!   *
echo *                                                         *
echo * If there is an error above, close this window, check    *
echo * that the phone is connected to this laptop and the      *
echo * laptop is also on the phone's Wifi Direct connection    *
echo * (ex: DIRECT-xx-9820-RC). Then open this program again   *
echo * to try again.                                           *
echo ***********************************************************
echo.

pause
