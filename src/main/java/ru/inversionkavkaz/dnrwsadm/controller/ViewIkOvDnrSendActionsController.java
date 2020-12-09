package ru.inversionkavkaz.dnrwsadm.controller;

import javafx.fxml.FXML;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrSendActions;

/**
 * @author  Valeriy Bugaev
 * @since   Tue Dec 08 22:29:28 MSK 2020
 */
public class ViewIkOvDnrSendActionsController extends JInvFXBrowserController 
{
    static class InitProp{
        static final String SEND_ID = "SEND_ID";
    }
    @FXML private JInvTable<PIkOvDnrSendActions> IK_OV_DNR_SEND_ACTIONS;
    @FXML private JInvToolBar toolBar;

    private final XXIDataSet<PIkOvDnrSendActions> dsIK_OV_DNR_SEND_ACTIONS = new XXIDataSet<> ();    

    private void initDataSet ()
    {
        dsIK_OV_DNR_SEND_ACTIONS.setTaskContext (getTaskContext ());
        dsIK_OV_DNR_SEND_ACTIONS.setRowClass (PIkOvDnrSendActions.class);
        dsIK_OV_DNR_SEND_ACTIONS.setWherePredicat("send_id="+getInitProperties().get(InitProp.SEND_ID));
        dsIK_OV_DNR_SEND_ACTIONS.setOrderBy("id");
    }

//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkOvDnrSendActions> dsfx = DSFXAdapter.bind (dsIK_OV_DNR_SEND_ACTIONS, IK_OV_DNR_SEND_ACTIONS, null, false); 

        dsfx.setEnableFilter (true);

        initToolBar ();

        IK_OV_DNR_SEND_ACTIONS.setToolBar (toolBar);       
        IK_OV_DNR_SEND_ACTIONS.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        

    private void doRefresh ()
    {
        IK_OV_DNR_SEND_ACTIONS.executeQuery ();
    }

    private void initToolBar ()
    {
        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.REFRESH);
    }
}

