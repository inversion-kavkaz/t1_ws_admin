package ru.inversionkavkaz.dnrwsadm.utils;

import ru.inversion.bicomp.stringconverter.DataSetStringConverter;
import ru.inversion.dataset.DataSetException;
import ru.inversion.dataset.SQLDataSet;
import ru.inversion.fx.form.controls.ILovValueControl;
import ru.inversion.fx.form.controls.JInvComboBox;
import ru.inversion.fx.form.lov.JInvEntityLov;

import java.util.ResourceBundle;

public class LovUtils {
    public static <T extends LovInterface, K> JInvEntityLov<T, K> bindLov(Class<T> lovClass, Class<K> keyClass, ILovValueControl idControl, Object nameControl, ResourceBundle resourceBundle) {
        JInvEntityLov<T, K> lov = new JInvEntityLov<>(lovClass);
        idControl.setLOV(lov);
        lov.setResourceBundle(resourceBundle);
        lov.bindControl(nameControl, T::getValue);
        return lov;
    }

//    public static <T extends LovInterface, K> void initCombobox(Class<T> lovClass, Class<K> keyClass, ILovValueControl idControl, Object nameControl, ResourceBundle resourceBundle) {
////        SQLDataSet<lovClass> dbfFormatSQLDataSet;
////        dbfFormatSQLDataSet = populateDataSet(POperType.class, null, null, "ID", 2);
////        DataSetStringConverter<POperType, Long> scOpertypeID = new DataSetStringConverter<>(dbfFormatSQLDataSet, POperType::getID, POperType::getCNAME);
////        OPERTYPE.setConverter(scOpertypeID);
////        OPERTYPE.getItems().addAll(scOpertypeID.keySet());
////        OPERTYPE.setValue(getDataObject().getFK_OPER_TYPE());
//    }

    public static <T extends LovInterface, K> JInvComboBox initCombobox(ru.inversion.tc.TaskContext taskContext, JInvComboBox<K, String> comboBox, Class<T> keyClass, Class<T> entityClass) throws DataSetException {
        Class<? extends T> rowClass;
        SQLDataSet<T> sqlDataSet = new SQLDataSet<>(taskContext, entityClass);
        sqlDataSet.execute();
        DataSetStringConverter<T, K> sc = new DataSetStringConverter<>(sqlDataSet, t -> (K) t.getKey(), t -> (String) t.getValue());
        comboBox.setConverter(sc);
        comboBox.getItems().addAll(sc.keySet());
        return comboBox;
    }

}
