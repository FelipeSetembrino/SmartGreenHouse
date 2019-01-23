package greenhouse.smart.smartgreenhouse.ArduinoConnectionPck;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ArduinoConnectionUsb {

    private Context context;
    private UsbInterface usbInterfaceFound = null;
    private UsbEndpoint endpointOut = null;
    private UsbEndpoint endpointIn = null;
    private UsbManager usbManager;
    private HashMap<String, UsbDevice> usbDevices;
    private UsbDeviceConnection usbDeviceConnection;
    private UsbDevice device;
    private final String ACTION_USB_PERMISSION = "USB_PERMISSION";
    private UsbDeviceConnection connection;
    private AlertDialog.Builder alertDialog;


    public ArduinoConnectionUsb(Context context){
        this.context = context;
        usbManager = (UsbManager) context.getSystemService(context.USB_SERVICE);
        alertDialog = new AlertDialog.Builder(context);
    }


    public void setConnection() {
        usbDevices = usbManager.getDeviceList();
        if (!usbDevices.isEmpty()) {

            boolean keep = true;
            for (Map.Entry<String, UsbDevice> entry : usbDevices.entrySet()) {
                device = entry.getValue();
                int deviceVID = device.getVendorId();
                //Arduino Vendor ID 0x2341
                if (deviceVID == 0x2341){

                    alertDialog.setMessage("Welcome to SmartGreenHouse!");
                    alertDialog.show();

                    PendingIntent pi = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
                    usbManager.requestPermission(device, pi);
                    keep = false;

                    setDevice(device);
                }
                else {
                    connection = null;
                    device = null;
                }
                if (!keep)
                    break;
            }
        }
        else {
            alertDialog.setMessage("Error! SmartGreenHouse not found!");
            alertDialog.show();
        }
    }

//    public void setConnection(Intent intentParam) {
//
//        Intent intent = intentParam;
//        String action = intent.getAction();
//
//        UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
//        if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
//            setDevice(device);
//        } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
//            if (usbDevices != null && usbDevices.equals(device)) {
//                setDevice(null);
//            }
//        }
//    }

    private void setDevice(UsbDevice device) {
        usbInterfaceFound = null;
        endpointOut = null;
        endpointIn = null;

        for (int i = 0; i < device.getInterfaceCount(); i++) {
            UsbInterface usbif = device.getInterface(i);

            UsbEndpoint tOut = null;
            UsbEndpoint tIn = null;

            int tEndpointCnt = usbif.getEndpointCount();
            if (tEndpointCnt >= 2) {
                for (int j = 0; j < tEndpointCnt; j++) {
                    if (usbif.getEndpoint(j).getType() == UsbConstants.USB_ENDPOINT_XFER_BULK) {
                        if (usbif.getEndpoint(j).getDirection() == UsbConstants.USB_DIR_OUT) {
                            tOut = usbif.getEndpoint(j);
                        } else if (usbif.getEndpoint(j).getDirection() == UsbConstants.USB_DIR_IN) {
                            tIn = usbif.getEndpoint(j);
                        }
                    }
                }

                if (tOut != null && tIn != null) {
                    // This interface have both USB_DIR_OUT
                    // and USB_DIR_IN of USB_ENDPOINT_XFER_BULK
                    usbInterfaceFound = usbif;
                    endpointOut = tOut;
                    endpointIn = tIn;
                }
            }

        }

        if (usbInterfaceFound == null) {
            return;
        }

        if (device != null) {
            UsbDeviceConnection connection =
                    usbManager.openDevice(device);
            if (connection != null &&
                    connection.claimInterface(usbInterfaceFound, true)) {
                usbDeviceConnection = connection;
                Thread thread = new Thread((Runnable) context);
                thread.start();

            } else {
                usbDeviceConnection = null;
            }
        }
    }

    public void setRun() {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        UsbRequest request = new UsbRequest();
        request.initialize(usbDeviceConnection, endpointIn);
        while (true) {
            request.queue(buffer, 1);
            if (usbDeviceConnection.requestWait() == request) {
                byte rxCmd = buffer.get(0);
                if(rxCmd!=0){
                    //bar.setProgress((int)rxCmd);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            } else {
                break;
            }
        }
    }

    public void sendCommand(int control) {
        synchronized (this) {

            if (usbDeviceConnection != null) {
                byte[] message = new byte[1];
                message[0] = (byte)control;
                usbDeviceConnection.bulkTransfer(endpointOut,
                        message, message.length, 0);
            }
        }
    }

    public void sendData(String control) {
        synchronized (this) {

            if (usbDeviceConnection != null) {
                byte[] message;
                message = control.getBytes();
                usbDeviceConnection.bulkTransfer(endpointOut,
                        message, message.length, 0);
            }
        }
    }
}
