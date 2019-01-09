package greenhouse.smart.smartgreenhouse.ArduinoConnectionPck;

import android.app.PendingIntent;

import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Felipe on 09/01/2019.
 */

public class ArduinoConnection {

    UsbManager usbManager;
    UsbDevice device;
    UsbDeviceConnection connection;
    public final String ACTION_USB_PERMISSION = "USB_PERMISSION";

    HashMap<String, UsbDevice> usbDevices = usbManager.getDeviceList();
        if (!usbDevices.isEmpty()) {
        boolean keep = true;
        for (Map.Entry<String, UsbDevice> entry : usbDevices.entrySet()) {
            device = entry.getValue();
            int deviceVID = device.getVendorId();
            if (deviceVID == 0x2341)//Arduino Vendor ID
            {
                PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
                usbManager.requestPermission(device, pi);
                keep = false;
            } else {
                connection = null;
                device = null;
            }

            if (!keep)
                break;
        }
    }

}
