package com.inveno.xiandu.applocation;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inveno.android.api.service.InvenoServiceContext;
import com.inveno.android.basics.service.BasicsServiceModule;
import com.inveno.android.device.param.provider.AndroidParamProviderHolder;
import com.inveno.xiandu.BuildConfig;
import com.inveno.xiandu.config.Const;
import com.inveno.xiandu.config.Keys;
import com.inveno.xiandu.db.DaoManager;
import com.inveno.xiandu.http.DDManager;
import com.inveno.xiandu.utils.SPUtils;

/**
 * Created by Administrator on 2016/9/23.
 */
public class MainApplication extends Application {
    private static Context sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Const.init();
        //初始化ARouter
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            ARouter.openLog();// 打印日志
            ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        //初始化sp存储
        SPUtils.init(Keys.SP_KEY, this);
        //网络引擎
        DDManager.init(this);
        //数据库管理工具
        DaoManager.getInstance(this);
        //基础服务模块
        BasicsServiceModule.Companion.onApplicationCreate(this);
        InvenoServiceContext.init(this);
    }

    public static Context getContext() {
        return sInstance;
    }
}
