package com.grupo.pinsa.libraries.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.grupo.pinsa.libraries.impresion.etiqueta.zebra.ImpresionEtiqueta;
import com.grupo.pinsa.libraries.impresion.etiqueta.zebra.ImpresionEtiquetaCallback;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Bluetooth {
    private static ArrayList<BluetoothDevice> listaDispositivos;
    private static BluetoothDevice dispositivoConectado;
    private static BluetoothAdapter bluetoothAdapter;
    private static Activity activity;
    private static boolean autoconnect;
    private static String deviceName;

    public static void start(Activity activity) {
        start(activity, false);
    }

    public static void start(Activity activity, Boolean autoConnect) {
        listaDispositivos = new ArrayList<>();
        setActivity(activity);
        autoconnect = autoConnect;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!status())
            on();
    }

    private static BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (listaDispositivos.size() < 1)
                    listaDispositivos.add(device);
                else {
                    boolean bEncontrado = false;

                    for (BluetoothDevice bluetoothDevice : listaDispositivos)
                        if (bluetoothDevice.getAddress().equals(device.getAddress()))
                            bEncontrado = true;

                    if (!bEncontrado)
                        listaDispositivos.add(device);
                }

                if (autoconnect && device.getAddress().equals(deviceName)) {
                    device.createBond();
                    ImpresionEtiqueta impresionEtiqueta = new ImpresionEtiqueta(activity);
                    impresionEtiqueta.print("MIN_ETIQUETA_INSUMOS", new ImpresionEtiquetaCallback() {});
                }
            }
        }
    };

    public static void connect() {
    }

    public static void off() {
        if (status())
            bluetoothAdapter.disable();
    }

    public static void on() {
        if (!status())
            bluetoothAdapter.enable();
    }

    public static ArrayList<BluetoothDevice> searching() {
        listaDispositivos.clear();

        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        activity.registerReceiver(receiver, intentFilter);
        bluetoothAdapter.startDiscovery();

        return listaDispositivos;
    }

    public static boolean status() {
        return bluetoothAdapter.isEnabled();
    }

    public static BluetoothDevice getConnectedDevice() {
        return dispositivoConectado;
    }

    /**
     * Set Methods
     */
    public static void setActivity(Activity ac) {
        activity = ac;
    }

    public static void setDeviceName(String name) {
        deviceName = name;
    }

    /**
     * Get Methods
     */
    public static ArrayList<BluetoothDevice> getDevices() {
        return listaDispositivos;
    }
}