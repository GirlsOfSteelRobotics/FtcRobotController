@echo off
echo Connecting to Moto Phone for wireless code upload...
adb tcpip 5555
adb connect 192.168.49.1
pause
