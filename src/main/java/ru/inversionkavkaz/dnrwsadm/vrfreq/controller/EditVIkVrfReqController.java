package ru.inversionkavkaz.dnrwsadm.vrfreq.controller;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.util.Callback;
import ru.inversion.bicomp.control.fxtask.BiCompTask;
import ru.inversion.fx.form.Alerts;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.StateEnum;
import ru.inversion.fx.form.controls.*;
import javafx.fxml.FXML;
import ru.inversion.fx.form.lov.JInvEntityLov;
import ru.inversion.utils.ConnectionStringFormatEnum;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrService;
import ru.inversionkavkaz.dnrwsadm.entity.PVerifyRequest;
import ru.inversionkavkaz.dnrwsadm.utils.DbUtils;
import ru.inversionkavkaz.dnrwsadm.utils.LovUtils;
import ru.inversionkavkaz.dnrwsadm.vrfreq.entity.PVIkVrfReq;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author porche
 * @since Mon Feb 15 16:50:47 MSK 2021
 */
public class EditVIkVrfReqController extends JInvFXFormController<PVIkVrfReq> {
    //    @FXML JInvLongField ID;
//    @FXML JInvTextField DCREATE;
    @FXML
    JInvTextField SERVICENAME;
    @FXML
    JInvTextField SERVICEDESCRIPTION;
    //    @FXML JInvTextField DATESTART;
//    @FXML JInvTextField DATEEND;
    @FXML
    JInvComboBox PAYELEMENTID;


//    @FXML JInvTextField STATUS;
//    @FXML JInvTextField STATUSDATE;
//    @FXML JInvTextField STATUSINFO;
//    @FXML JInvTextField RESPONCE_EXISTS;
//    @FXML JInvTextField REPORTDATA_EXISTS;
//    @FXML JInvTextField FILENAME;
//    @FXML JInvTextField SAVEDUSR;
//    @FXML JInvTextField SAVEDDATE;

    Task payVerifierTask = null;
//
// Initializes the controller class.
//

    @Override
    protected void init() throws Exception {
        super.init();
        initLov();
    }

    private void initLov() {
        JInvEntityLov<PIkOvDnrService, String> lov = LovUtils.bindLov(PIkOvDnrService.class, String.class, SERVICENAME, SERVICEDESCRIPTION,
                ResourceBundle.getBundle("ru/inversionkavkaz/dnrwsadm/vrfreq/controller/res/PIkOvDnrService"));
        lov.checkValue(getDataObject().getSERVICENAME(), true);
        lov.setWherePredicat("COMPARISONURL is not null");
        lov.bindControl(PAYELEMENTID, new Callback<PIkOvDnrService, Object>() {
            @Override
            public Object call(PIkOvDnrService ikOvDnrService) {
                PAYELEMENTID.getItems().clear();
                if (ikOvDnrService.getPAYELEMENTIDS() != null) {
                    PAYELEMENTID.getItems().addAll(ikOvDnrService.getPAYELEMENTIDS().split(","));
                }
                PAYELEMENTID.setDisable(PAYELEMENTID.getItems().isEmpty());
                PAYELEMENTID.setRequired(!PAYELEMENTID.getItems().isEmpty());
                return null;
            }
        });
        PAYELEMENTID.setDisable(true);
        PAYELEMENTID.setRequired(false);
//        PIkOvDnrService pIkOvDnrService = new PIkOvDnrService();
//        pIkOvDnrService.
//        StringProperty property =  new SimpleStringProperty();
//        lov.bindProperty(property, PIkOvDnrService::getCOMPARISONURL);
//        lov.bindControl(getDataObject().getURL(), PIkOvDnrService::getCOMPARISONURL);
    }

    String buildRunArgs(PVIkVrfReq pvIkVrfReq) {
//        String connectionString = getTaskContext().getConnectionString(ConnectionStringFormatEnum.SQL_SIMPLE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String connectionString = getTaskContext().getConnectionString(ConnectionStringFormatEnum.SQL_PLUS);
        String serviceName = "VRF";
        String url = "http:\\test.com";
        String args = String.format("-o %s -c %s -s %s -f %s -u %s -t %s", connectionString, serviceName,
                pvIkVrfReq.getDATESTART().format(formatter), pvIkVrfReq.getDATEEND().format(formatter), url, pvIkVrfReq.getID());
        return connectionString;
    }

    private void sendRequest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String connectionString = getTaskContext().getConnectionString(ConnectionStringFormatEnum.SQL_PLUS);
        payVerifierTask = new BiCompTask() {
            @Override
            protected Object call() throws Exception {
                File file = new File("i://japp//citypayverifier-1.0-SNAPSHOT-jar-with-dependencies.jar");
                URLClassLoader child = new URLClassLoader(new URL[]{file.toURI().toURL()}, ClassLoader.getSystemClassLoader());
                Class classToLoad = Class.forName("ru.inversionkavkaz.citypayverifier.Main", true, child);
                Class programOptionsClass = Class.forName("ru.inversionkavkaz.payverifierscore.ProgramOption", true, child);

                Method method = classToLoad.getDeclaredMethod("send", Map.class);
                Object instance  = classToLoad.newInstance();
                Map<String, String> programOptions = new HashMap();
                programOptions.put("o",connectionString);
                programOptions.put("c", getDataObject().getSERVICENAME());
                programOptions.put("s",getDataObject().getDATESTART().format(formatter));
                programOptions.put("f",getDataObject().getDATEEND().format(formatter));
                if(getDataObject().getPAYELEMENTID()!=null)
                    programOptions.put("e",getDataObject().getPAYELEMENTID().toString());
                programOptions.put("t",getDataObject().getID().toString());
                programOptions.put("d","X");

                String result = (String) method.invoke(instance, programOptions);
                System.out.println("result="+result);
                if(result!=null){
                    throw new RuntimeException("Ошибка внешнего процесса: " + result);
                }
//                Object instance2 = programOptionsClass.newInstance();
//                Object result = method.invoke(instance);
//                System.out.println("call 1");
//                File file = new File("i://japp//citypayverifier-1.0-SNAPSHOT-jar-with-dependencies.jar");
//                System.out.println("call 2");
//                URLClassLoader child = new URLClassLoader (new URL[] {file.toURI().toURL()}, ClassLoader.getSystemClassLoader());
//                System.out.println("call 3");
//                Class<ru.inversionkavkaz.payverifierscore.Starter> clazz = (Class<Starter>) Class.forName("ru.inversionkavkaz.citypayverifier.Main", true, child);
//                ru.inversionkavkaz.payverifierscore.Starter starter = clazz.newInstance();
//
//                System.out.println("call 4");
//                java.lang.reflect.Method method = starter.getClass().getMethod("send", ru.inversionkavkaz.payverifierscore.ProgramOption.class);
//                System.out.println("call 5");
//
//                ProgramOption programOption = new ProgramOption();
//                programOption.connection = connectionString;// "xxi/NEW8I@odb12";
//                programOption.cpsevdo = getDataObject().getSERVICENAME();//  "VRF";
//                programOption.dateStart = getDataObject().getDATESTART().format(formatter);// "20190101235959";
//                programOption.dateEnd = getDataObject().getDATEEND().format(formatter);//"20201118235959";
//                if(getDataObject().getPAYELEMENTID()!=null)
//                    programOption.payElementID = getDataObject().getPAYELEMENTID().toString();
//                programOption.transactionID =  getDataObject().getID().toString();//"7";
//                programOption.reportOutDir = "X"; //не выгружать отчет, только сформировать
//
//                System.out.println("call 6");
//                String result = (String) method.invoke(starter, programOption);
//                System.out.println("call 7");
//
//                System.out.println("result="+result);
//                if(result!=null){
//                    throw new RuntimeException("Ошибка внешнего процесса: " + result);
//                }
//
//
////                Object instance = classToLoad.newInstance();
////                Object result = method.invoke(instance);
//
////                Class classToLoad = Class.forName("ru.inversionkavkaz.citypayverifier.Main", true, child);
////                Method method = classToLoad.getDeclaredMethod("myMethod");
////                Object instance = classToLoad.newInstance();
////                Object result = method.invoke(instance);
//
//
////                System.out.println("in call 1");
////                Class<ru.inversionkavkaz.payverifierscore.Starter> clazz = (Class<Starter>) Class.forName("ru.inversionkavkaz.citypayverifier.Main");
////                System.out.println("in call 2");
////                ru.inversionkavkaz.payverifierscore.Starter starter = clazz.newInstance();
////                System.out.println("in call 3");
////                java.lang.reflect.Method method;
////                method = starter.getClass().getMethod("send", ru.inversionkavkaz.payverifierscore.ProgramOption.class);
////                System.out.println("in call 4");
////                ProgramOption programOption = new ProgramOption();
////                programOption.connection = connectionString;// "xxi/NEW8I@odb12";
////                programOption.cpsevdo = getDataObject().getSERVICENAME();//  "VRF";
////                programOption.dateStart = getDataObject().getDATESTART().format(formatter);// "20190101235959";
////                programOption.dateEnd = getDataObject().getDATEEND().format(formatter);//"20201118235959";
////                if(getDataObject().getPAYELEMENTID()!=null)
////                    programOption.payElementID = getDataObject().getPAYELEMENTID().toString();
////                programOption.transactionID =  getDataObject().getID().toString();//"7";
////                programOption.reportOutDir = "X"; //не выгружать отчет, только сформировать
////                System.out.println("in call 5");
////                String result = (String) method.invoke(starter, programOption);
////                System.out.println("in call 6");
////                System.out.println("result="+result);
//////                String result =  new ru.inversionkavkaz.citypayverifier.Main().send(programOption);
////                if(result!=null){
////                    throw new RuntimeException("Ошибка внешнего процесса: " + result);
////                }
                return this;
            }
        };
        payVerifierTask.setOnFailed(this::onFailed);
        payVerifierTask.setOnCancelled(this::onCancelled);
        payVerifierTask.setOnSucceeded(this::onSucceded);

        new Thread(payVerifierTask, "Verification in progress").start();
        setState(StateEnum.WAIT);
    }

    private void onCancelled(Event event) {
        setState(StateEnum.ACTIVE);
        String errorMessage = "Внешний процесс сверки приостановлен. ";
        if (payVerifierTask != null && payVerifierTask.getException() != null && payVerifierTask.getException().getMessage() != null) {
            errorMessage += this.getException().getMessage();
            payVerifierTask.getException().printStackTrace();
        }
        System.out.println(errorMessage);
        Alerts.error(this, "Выполнение внешнего процесса сверкиЖ", "Ошибка при выполнении внешнего процесса сверки", errorMessage);
    }

    private void onFailed(Event event) {
        setState(StateEnum.ACTIVE);
        String errorMessage = "Внешний процесс сверки завершен с ошибкой. ";
        if (payVerifierTask != null && payVerifierTask.getException() != null) {
            errorMessage += payVerifierTask.getException().getMessage();
            payVerifierTask.getException().printStackTrace();
        }
        System.out.println(errorMessage);
        Alerts.error(this, "Выполнение внешнего процесса сверки", "Ошибка при выполнении внешнего процесса сверки", errorMessage);

        if (payVerifierTask != null && payVerifierTask.getException() != null && payVerifierTask.getException().getClass() == RuntimeException.class)
            closeNow(FormReturnEnum.RET_OK);
    }

    private void onSucceded(Event event) {
        System.out.println("Получение реестра платежей от провайдера завершено.");
        Alerts.info(this, "Получение реестра платежей от провайдера завершено.");
        closeNow(FormReturnEnum.RET_OK);
    }

    @Override
    protected boolean onOK() {
        if (getFormMode().equals(FormModeEnum.VM_INS)) {
            getFXEntity().commit();
//            String args = buildRunArgs(getDataObject());
            sendRequest();
            return false;
        }
        return super.onOK();
    }
}

