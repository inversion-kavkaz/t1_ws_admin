package ru.inversionkavkaz.dnrwsadm.controller;

import javafx.fxml.FXML;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrSendA;

/**
 * @author  Valeriy Bugaev
 * @since   Wed Dec 09 12:07:24 MSK 2020
 */
public class ViewIkOvDnrSendAController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkOvDnrSendA> IK_OV_DNR_SEND_A;
    @FXML private JInvToolBar toolBar;

    static class InitProp{
        static final String SEND_ID = "SEND_ID";
    }

    private final XXIDataSet<PIkOvDnrSendA> dsIK_OV_DNR_SEND_A = new XXIDataSet<> ();
    private void initDataSet ()
    {
        dsIK_OV_DNR_SEND_A.setTaskContext (getTaskContext ());
        dsIK_OV_DNR_SEND_A.setRowClass (PIkOvDnrSendA.class);
        dsIK_OV_DNR_SEND_A.setWherePredicat("send_id="+getInitProperties().get(InitProp.SEND_ID));
        dsIK_OV_DNR_SEND_A.setOrderBy("i_id");
    }
//
// Initializes the controller class.
//

    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));

        initDataSet ();
        DSFXAdapter<PIkOvDnrSendA> dsfx = DSFXAdapter.bind (dsIK_OV_DNR_SEND_A, IK_OV_DNR_SEND_A, null, false);

        dsfx.setEnableFilter (true);


        initToolBar ();

        IK_OV_DNR_SEND_A.setToolBar (toolBar);
        IK_OV_DNR_SEND_A.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }

    private void doRefresh ()
    {
        IK_OV_DNR_SEND_A.executeQuery ();
    }

    private void initToolBar ()
    {
        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.REFRESH);
    }
}

