package com.example.demo;

import com.example.demo.model.District;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class NativeSql {

    private static SessionFactory factory;

    // Method to READ all the employees using Scalar Query
    public District listDistrict(String point) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<District> data = new ArrayList<>();
        List<Object> rows = new ArrayList();
        Map map = new HashMap();
        try {
            tx = session.beginTransaction();
            String sql = "SELECT name_2, name_1, ST_AsGeoJson(geom) as geo  from gadm36_vnm_2 where ST_Within('"+point+"', ST_AsText(geom)) ";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            rows = query.list();
            for (Object row : rows) {
                map = (Map) row;
                District district= new District();
                district.setName((String)map.get("name_2"));
                district.setProvince((String)map.get("name_1"));
                district.setGeometry((String)map.get("geo"));
                
                data.add(district);
                System.out.println(map.get("name_2") + " Tinh " + map.get("name_1") );
            }
            tx.commit();
            return data.get(0);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public NativeSql() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    // Method to READ all the employees using Entity Query
}
