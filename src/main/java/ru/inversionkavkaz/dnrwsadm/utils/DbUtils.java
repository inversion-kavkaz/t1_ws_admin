package ru.inversionkavkaz.dnrwsadm.utils;

import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.fx.app.es.JInvErrorService;
import ru.inversion.fx.form.AbstractBaseController;
import ru.inversion.fx.form.Alerts;
import ru.inversion.tc.TaskContext;

import javax.persistence.GeneratedValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {

    public static Long getSeqID(TaskContext tc, XXIDataSet dataSet) {
        return getSeqID(tc, dataSet.getRowClass());
    }

    public static Long getSeqID(TaskContext tc, Class entityClass) {
        long res = 0;
        ResultSet result = null;
        try {
            for (Method method : entityClass.getDeclaredMethods()) {
                Annotation annotation = method.getAnnotation(GeneratedValue.class);
                if (annotation != null && annotation instanceof GeneratedValue) {
                    String generatorName = ((GeneratedValue) annotation).generator();
                    if (!generatorName.isEmpty()) {
                        Statement statement = null;
                        statement = tc.getConnection().createStatement();
                        //Выполним запрос
                        result = statement.executeQuery("select " + generatorName + ".nextval from dual");
                        result.next();
                        res = result.getLong(1);
                        result.close();
                        return res;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
//
//    public static boolean isOdbAction(AbstractBaseController controller, int value) {
//        try {
//            HashMap m1 = new HashMap ();
//            m1.put ("idact", value);
//            controller.executeSQLExpression(controller.getClass().getResource("/ru/inversionkavkaz/jobman/controller/res/plsql/def.xml"), "CheckOdbAction", m1);
//            return (Long) m1.get("rc") != 0L;
//        } catch (SQLExpressionException ex) {
//            controller.handleException(ex);
//        }
//        return false;
//    }

//    public static Object deleteRecords(AbstractBaseController controller, Object parentWindow, XXIDataSet dataSet, String xmlDeleteProcName){
//        if(dataSet.hasMarkedRows()){
//            if(Alerts.yesNo(parentWindow,"Удаление записей","Удалить помеченные записи?")){
//                new ParamMap()
//                        .add("idmarker", dataSet.getMarkerID())
//                        .execWithDecor(controller, ru.inversionkavkaz.minfinex.utils.DbUtils.class.getResource("/ru/inversionkavkaz/restman/controller/res/plsql/def.xml"),
//                                xmlDeleteProcName, paramMap -> {
//                                    try {
//                                        dataSet.clearMark();
//                                        dataSet.getTaskContext().commit();
//                                        dataSet.execute();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }, null, null);
//                return null;
//            }
//        }else{
//            return dataSet.getCurrentRow();
//        }
//        return null;
//    }

    public static boolean deleteByMarker(AbstractBaseController parentWindow, TaskContext taskContext, String table, String idField, XXIDataSet dataSet) {
        return deleteByMarker(parentWindow, taskContext, table, idField, dataSet, "delete from %s where %s in (select idrow from mrk_id where idmarker = ?)");
    }

    public static boolean deleteByMarker(AbstractBaseController parentWindow, TaskContext taskContext, String table, String idField, XXIDataSet dataSet, String sql) {
        if (Alerts.yesNo(parentWindow, "Удаление", "Удалить помеченные записи?")) {
            try {
                PreparedStatement statement = taskContext.getConnection().prepareStatement(String.format(sql, table, table + "." + idField));
                statement.setLong(1, dataSet.getMarkerID());
                statement.executeQuery();
                statement.close();
                taskContext.commit();
                return true;
            } catch (SQLException var7) {
                JInvErrorService.handleException((Object)null, var7);
                return false;
            }
        } else {
            return false;
        }
    }

}
