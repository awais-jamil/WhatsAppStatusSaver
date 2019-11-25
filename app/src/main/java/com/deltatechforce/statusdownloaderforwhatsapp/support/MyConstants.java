package com.deltatechforce.statusdownloaderforwhatsapp.support;

import android.os.Environment;

import java.io.File;

public class MyConstants {

    public static final File WHATSAPP_STATUS_DIRECTORY = new File(Environment.getExternalStorageDirectory()
            + File.separator + "WhatsApp/Media/.Statuses");

    public static final File GBWHATSAPP_STATUS_DIRECTORY = new File(Environment.getExternalStorageDirectory()
           + "/GBWhatsApp/Media/.Statuses");

    public static final File BUSSINESS_WHATSAPP_STATUS_DIRECTORY = new File(Environment.getExternalStorageDirectory()
            + File.separator + "WhatsApp/Media/.Statuses");

    public static final String SAVED_DIRECTORY = Environment.getExternalStorageDirectory()
            + File.separator + "WhatsAppStatusDownloader";

    public static int THUMBSIZE = 1080;

}
