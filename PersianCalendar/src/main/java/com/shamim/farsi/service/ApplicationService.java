package com.shamim.farsi.service;


        Utils utils = Utils.getInstance(getBaseContext());
        utils.loadApp();
        updateUtils.update(true);

        return START_STICKY;
    }
}
