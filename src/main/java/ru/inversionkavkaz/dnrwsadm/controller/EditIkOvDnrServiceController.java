package ru.inversionkavkaz.dnrwsadm.controller;

import ru.inversion.bicomp.stringconverter.DataSetStringConverter;
import ru.inversion.dataset.DataSetException;
import ru.inversion.dataset.SQLDataSet;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.*;
import javafx.fxml.FXML;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrService;
import ru.inversionkavkaz.dnrwsadm.protocol.entity.PIkOvDnrServiceProtocol;
import ru.inversionkavkaz.dnrwsadm.ovplat.entity.POvPlat;
import ru.inversionkavkaz.dnrwsadm.utils.LovUtils;

import java.util.Collections;
import java.util.ResourceBundle;

/**
 * @author  bvv
 * @since   Fri Feb 07 13:05:59 MSK 2020
 */
public class EditIkOvDnrServiceController extends JInvFXFormController <PIkOvDnrService>
{  
    @FXML JInvTextField CNAME;
    @FXML JInvTextField IDOV_PLAT;
    @FXML JInvTextField IDOV_PLAT_NAME;
//    @FXML JInvLongField IENABLED;
//    @FXML JInvTextField CDESCRIPTION;
//    @FXML JInvTextField SECURITY_TAG;
//    @FXML JInvTextField CURL;

    @FXML
    JInvComboBox <String, String> PROTOCOL;

//
// Initializes the controller class.
//
    @Override
    protected void init () throws Exception 
    {
        super.init ();
        initLov();
        initCombobox();
    }

    private void initLov(){
//        JInvEntityLov<POvPlat, String> ovPlatLov = new JInvEntityLov(POvPlat.class);
//        IDOV_PLAT.setLOV(ovPlatLov);
//        ovPlatLov.setResourceBundle(ResourceBundle.getBundle("ru/inversionkavkaz/dnrwsadm/ovplat/controller/res/POvPlat"));
//        ovPlatLov.bindControl(IDOV_PLAT_NAME, ovplat -> ovplat.getCNAMEOPER());
        LovUtils.bindLov(POvPlat.class, Long.class, IDOV_PLAT, IDOV_PLAT_NAME, ResourceBundle.getBundle("ru/inversionkavkaz/dnrwsadm/ovplat/controller/res/POvPlat"))
                .checkValue(getDataObject().getIDOV_PLAT(), true);
    }

    private void initCombobox() throws DataSetException {
        LovUtils.initCombobox(getTaskContext(), PROTOCOL, PIkOvDnrServiceProtocol.class, PIkOvDnrServiceProtocol.class);
//        SQLDataSet<PIkOvDnrServiceProtocol> dsProtocol = null;
//        try {
//            dsProtocol = populateDataSet (PIkOvDnrServiceProtocol.class, null, null, "CPROTOCOL", Collections.EMPTY_MAP,2);
//        } catch (DataSetException ex) {
//            handleException (ex);
//        }
//        DataSetStringConverter scProtocol  = new DataSetStringConverter <> (dsProtocol, PIkOvDnrServiceProtocol::getCPROTOCOL, PIkOvDnrServiceProtocol::getCPROTOCOL);
//        PROTOCOL.setConverter(scProtocol);
//        PROTOCOL.getItems().addAll(scProtocol.keySet());


//        for (final File file : files) {
//            reportListComboBox.getItems().add(new Report(file));
//        }

//        reportListComboBox.setCellFactory(p -> new ListCell<Report>() {
//            @Override
//            protected void updateItem(Report item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item != null && !empty) {
//                    setText(item.toString());
//                } else {
//                    setText(null);
//                }
//            }
//        });
//        if (!reportListComboBox.getItems().isEmpty())
//            reportListComboBox.getSelectionModel().select(0);

        //BundleStringConverter.setupFor(getClass(), "CusFlagLov", CCUSFLAG);
    }

    @Override
    protected boolean onOK() {
        CNAME.setText(CNAME.getText().toUpperCase());
        return super.onOK();
    }
}

