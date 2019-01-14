package greenhouse.smart.smartgreenhouse.ArduinoConnectionPck;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class ArduinoConnection {

    private Context context;
    private UsbDevice device;
    private UsbManager usbManager;
    private UsbDeviceConnection connection;
    private final String ACTION_USB_PERMISSION = "USB_PERMISSION";
    private HashMap<String, UsbDevice> usbDevices;

    AlertDialog.Builder builder1;

    public ArduinoConnection(Context context){
        this.context = context;
        usbManager = (UsbManager) context.getSystemService(context.USB_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
//        registerReceiver(broadcastReceiver, filter);

        builder1 = new AlertDialog.Builder(context);

    }

    public void setConnection() {
        usbDevices = usbManager.getDeviceList();
        if (!usbDevices.isEmpty()) {

            builder1.setMessage("Achou");
            builder1.show();

            boolean keep = true;
            for (Map.Entry<String, UsbDevice> entry : usbDevices.entrySet()) {
                device = entry.getValue();
                int deviceVID = device.getVendorId();
                if (deviceVID == 0x2341)//Arduino Vendor ID
                {

                    builder1.setMessage("Bem vindo ao Arduino!");
                    builder1.show();

                    PendingIntent pi = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
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
        else {
            builder1.setMessage("Fica para proxima");
            builder1.show();
        }
    }

}
