package com.inveno.android.basics.service

import android.content.Context
import com.inveno.android.basics.appcompat.context.ContextHolder
import com.inveno.android.basics.service.thread.ThreadUtil

class BasicsServiceModule {
    companion object{
        fun onApplicationCreate(context:Context){
            ThreadUtil.Installer.install()
            ContextHolder.Companion.Operate.setAppContext(context)
        }
    }
}