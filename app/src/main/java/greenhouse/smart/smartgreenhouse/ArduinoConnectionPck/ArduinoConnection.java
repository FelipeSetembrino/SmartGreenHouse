package greenhouse.smart.smartgreenhouse.ArduinoConnectionPck;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ArduinoConnection {

    private Context context;
    private UsbDevice device;
    private UsbManager usbManager;
    private UsbDeviceConnection connection;
    private final String ACTION_USB_PERMISSION = "USB_PERMISSION";
    private HashMap<String, UsbDevice> usbDevices;
    private UsbSerialDevice serialPort;

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

    private void setConnection() {
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

    private void setSerialPort(){
        connection = usbManager.openDevice(device);
        serialPort = UsbSerialDevice.createUsbSerialDevice(device, connection);
    }

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { //Broadcast Receiver to automatically start and stop the Serial connection.
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_USB_PERMISSION)) {
                boolean granted = intent.getExtras().getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED);
                if (granted) {
                    setSerialPort();
                    if (serialPort != null) {
                        if (serialPort.open()) { //Set Serial Connection Parameters.
                            serialPort.setBaudRate(9600);
                            serialPort.setDataBits(UsbSerialInterface.DATA_BITS_8);
                            serialPort.setStopBits(UsbSerialInterface.STOP_BITS_1);
                            serialPort.setParity(UsbSerialInterface.PARITY_NONE);
                            serialPort.setFlowControl(UsbSerialInterface.FLOW_CONTROL_OFF);
                        } else {
                            builder1.setMessage("PORT NOT OPEN");
                            builder1.show();
                            //Log.d("SERIAL", "PORT NOT OPEN");
                        }
                    } else {
                        builder1.setMessage("PORT WITHOUT ARDUINO");
                        builder1.show();
                        //Log.d("SERIAL", "PORT IS NULL");
                    }
                } else {
                    builder1.setMessage("PERM NOT GRANTED");
                    builder1.show();
                    //Log.d("SERIAL", "PERM NOT GRANTED");
                }
            } else if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_ATTACHED)) {
                setConnection();
            } else if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_DETACHED)) {
                // onClickStop(stopButton);

            }
        }
    };

    public void setArduinoData(String data){
        serialPort.write(data.getBytes());
    }

    public String getArduinoData(){
        serialPort.read(mCallback);
        return mCallback.toString();
    }

    UsbSerialInterface.UsbReadCallback mCallback = new UsbSerialInterface.UsbReadCallback() { //Defining a Callback which triggers whenever data is read.
        String data = null;
        @Override
        public void onReceivedData(byte[] arg0) {
            try {
                data = new String(arg0, "UTF-8");
                data.concat("/n");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        @Override
        public String toString(){
            return data;
        }
    };

}
