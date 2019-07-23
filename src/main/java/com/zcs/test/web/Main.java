package com.zcs.test.web;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.*;
import java.util.List;

public class Main{
    public static void main(String[] args) throws AWTException, InterruptedException {
        final List<WindowInfo> inflList = new ArrayList<>();
        final List<Integer> order = new ArrayList<>();
        int top = User32.instance.GetTopWindow(0);
        Thread.sleep(3000);
        while (top!=0) {
            order.add(top);
            top = User32.instance.GetWindow(top, User32.GW_HWNDNEXT);
        }
        User32.instance.EnumWindows((hWnd, lParam) -> {
            if (User32.instance.IsWindowVisible(hWnd)) {
                RECT r = new RECT();
                User32.instance.GetWindowRect(hWnd, r);
                if (r.left>-32000) {     // minimized
                    byte[] buffer = new byte[1024];
                    User32.instance.GetWindowTextA(hWnd, buffer, buffer.length);
                    String title = Native.toString(buffer);
                    inflList.add(new WindowInfo(hWnd, r, title));
                }
            }
            return true;
        }, 0);
        Collections.sort(inflList, Comparator.comparingInt(o -> order.indexOf(o.hwnd)));
        Robot robot = new Robot();
        for (WindowInfo w : inflList) {
            System.out.println(w);
            if(w.title.contains("QQ飞车")){
//                while (true){
                    robot.mouseMove(100,150);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
//                    Thread.sleep(4*60*1000);
//                }
            }
        }
    }

    public static interface WndEnumProc extends StdCallLibrary.StdCallCallback {
        boolean callback (int hWnd, int lParam);
    }

    public static interface User32 extends StdCallLibrary
    {
        final User32 instance = (User32) Native.loadLibrary ("user32", User32.class);
        boolean EnumWindows (WndEnumProc wndenumproc, int lParam);
        boolean IsWindowVisible(int hWnd);
        int GetWindowRect(int hWnd, RECT r);
        void GetWindowTextA(int hWnd, byte[] buffer, int buflen);
        int GetTopWindow(int hWnd);
        int GetWindow(int hWnd, int flag);
        final int GW_HWNDNEXT = 2;
    }

    public static class RECT extends Structure {
        public int left,top,right,bottom;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList("top", "left", "right", "bottom");
        }
    }
    public static class WindowInfo {
        int hwnd;
        RECT rect;
        String title;
        public WindowInfo(int hwnd, RECT rect, String title)
        { this.hwnd = hwnd; this.rect = rect; this.title = title; }

        public String toString() {
            return String.format("(%d,%d)-(%d,%d) : \"%s\"",
                    rect.left,rect.top,rect.right,rect.bottom,title);
        }
    }
}