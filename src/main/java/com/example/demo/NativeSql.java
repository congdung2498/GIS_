package com.example.demo;

import com.example.demo.model.District;
import com.example.demo.model.Tree;
import com.example.demo.model.Village;
import java.math.BigInteger;
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
    public Village listVillage(String point) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Village> data = new ArrayList<>();
        List<Object> rows = new ArrayList();
        Map map = new HashMap();
        try {
            tx = session.beginTransaction();
            String sql = "SELECT name_3, name_2, name_1, dientich, danso, ST_AsGeoJson(geom) as geo  from xa where ST_Within('" + point + "', ST_AsText(geom)) ";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            rows = query.list();
            for (Object row : rows) {
                map = (Map) row;
                Village village = new Village();
                village.setName((String) map.get("name_3"));
                village.setDistrict((String) map.get("name_2"));
                village.setGeometry((String) map.get("geo"));
                village.setAcreage((Double) map.get("dientich"));
                village.setPopulartion((Integer) map.get("danso"));

                data.add(village);
                System.out.println(map.get("name_2") + "Thành Phố " + map.get("name_1") + "Dien tich" + map.get("dientich") + "Dân số" + map.get("danso"));
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
    
    public List<Village> listVillageGeo(String point) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Village> data = new ArrayList<>();
        List<Object> rows = new ArrayList();
        Map map = new HashMap();
        String geo ="";
        try {
            tx = session.beginTransaction();
            String sql = "SELECT ST_AsText(geom) as geo from xa where ST_Within('" + point + "', ST_AsText(geom)) ";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            rows = query.list();
            for (Object row : rows) {
                map = (Map) row;
                geo = (String) map.get("geo");
                System.out.println(geo);
            }
            tx.commit();
            if(geo != null && geo.length()>0){
                return listVillageTouch(geo);
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
       
         return null;
    }
    
     public List<Village> listVillageTouch(String point) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Village> data = new ArrayList<>();
        List<Object> rows = new ArrayList();
        Map map = new HashMap();
        try {
            tx = session.beginTransaction();
            String sql = "SELECT name_3, name_2, name_1, dientich, danso, ST_AsGeoJson(geom) as geo  from xa where ST_Touches('" + point + "', ST_AsText(geom))";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            rows = query.list();
            for (Object row : rows) {
                map = (Map) row;
                Village village = new Village();
                village.setName((String) map.get("name_3"));
                village.setDistrict((String) map.get("name_2"));
                village.setGeometry((String) map.get("geo"));
                village.setAcreage((Double) map.get("dientich"));
                village.setPopulartion((Integer) map.get("danso"));

                data.add(village);
                System.out.println(map.get("name_2") + "Thành Phố " + map.get("name_1") + "Dien tich" + map.get("dientich") + "Dân số" + map.get("danso"));
            }
            tx.commit();
            return data;
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
    
    
    

    public List<Tree> getTree() {
        List<Tree> tree = getAllDistricttoTree();
        for (Tree dt : tree) {
            if (dt.getId() != null) {
                List<Tree> vlge = getVillageByDistrictid(dt.getId());
                dt.setChildren(vlge);
            }
        }
        return tree;
    }

    public List<Tree> getVillageByDistrictid(String districtID) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Tree> data = new ArrayList<>();
        List<Object> rows = new ArrayList();
        Map map = new HashMap();
        try {
            tx = session.beginTransaction();
            String sql = "SELECT gid_3, name_3 from xa where gid_2 like '" + districtID + "'";

            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            rows = query.list();
            for (Object row : rows) {
                map = (Map) row;
                Tree tree = new Tree();
                tree.setId((String) map.get("gid_3"));
                tree.setText((String) map.get("name_3"));
                tree.setIsVillage(true);
                data.add(tree);
            }
            tx.commit();
            return data;
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

    public List<Tree> getAllDistricttoTree() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Tree> data = new ArrayList<>();
        List<Object> rows = new ArrayList();
        Map map = new HashMap();
        try {
            tx = session.beginTransaction();
            String sql = "SELECT gid_2, name_2 from huyen";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            rows = query.list();
            for (Object row : rows) {
                map = (Map) row;
                Tree tree = new Tree();
                tree.setId((String) map.get("gid_2"));
                tree.setText((String) map.get("name_2"));
                tree.setIsVillage(false);
                data.add(tree);
            }
            tx.commit();
            return data;
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

    public Object getDistrictOrVillageByid(String gid) {
        District district = getDistrictbyId(gid);
        if (district != null && district.getGid() != null) {
            return district;
        } else {
            Village village = getVillagebyId(gid);
            if (village != null && village.getGid() != null) {
                return village;
            }
        }
        return null;
    }

    public Village getVillagebyId(String geoId) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Village> data = new ArrayList<>();
        List<Object> rows = new ArrayList();
        Map map = new HashMap();
        try {
            tx = session.beginTransaction();
            String sql = "SELECT gid_3,name_3, name_2, name_1, dientich, danso, ST_AsGeoJson(geom) as geo  from xa where gid_3 like '" + geoId + "'";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            rows = query.list();
            for (Object row : rows) {
                map = (Map) row;
                Village village = new Village();
                village.setGid((String) map.get("gid_3"));
                village.setName((String) map.get("name_3"));
                village.setDistrict((String) map.get("name_2"));
                village.setGeometry((String) map.get("geo"));
                village.setAcreage((Double) map.get("dientich"));
                village.setPopulartion((Integer) map.get("danso"));
                data.add(village);
            }
            tx.commit();
            if (data != null && data.size() > 0) {
                return data.get(0);
            } else {
                return null;
            }
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

    public District getDistrictbyId(String geoId) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<District> data = new ArrayList<>();
        List<Object> rows = new ArrayList();
        Map map = new HashMap();
        try {
            tx = session.beginTransaction();
            String sql = "SELECT  huyen.gid_2,huyen.name_2, huyen.dientich, ST_AsGeoJson(huyen.geom),"
                    + " (select sum(danso) from xa where xa.gid_2 = huyen.gid_2) as tongdan from huyen "
                    + "where huyen.gid_2 like '" + geoId + "'";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            rows = query.list();
            for (Object row : rows) {
                map = (Map) row;
                District district = new District();
                district.setGid((String) map.get("gid_2"));
                district.setName((String) map.get("name_2"));
                district.setAcreage((Integer) map.get("dientich"));
                district.setPopulartion((BigInteger) map.get("tongdan"));
                district.setGeometry((String) map.get("geo"));
                data.add(district);
            }
            tx.commit();
            if (data != null && data.size() > 0) {
                return data.get(0);
            } else {
                return null;
            }
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
