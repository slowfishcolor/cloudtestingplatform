package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.FileDao;
import com.mist.cloudtestingplatform.model.File;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prophet on 2018/1/19.
 */
@Repository
public class FileDaoImpl extends DaoBase implements FileDao {

    @Override
    public File getFileByName(String fileName) {
        String hql = "from File where name = :fName";
        List<File> rs = currentSession().createQuery(hql)
                .setString("fName", fileName).list();
        if (rs != null && rs.size() > 0) {
            return rs.get(0);
        }
        return null;
    }

    @Override
    public void saveFile(File file) {
        currentSession().saveOrUpdate(file);
    }

    @Override
    public List<File> listAllFilesWithoutData() {
        List<File> items = new ArrayList<>();
        String hql = "select id, name, updateTime, remark, type from File";
        List<Object[]> datas = currentSession().createQuery(hql).list();
        for (Object[] data: datas) {
            File file = new File();
            file.setId((Integer) data[0]);
            file.setName((String) data[1]);
            file.setUpdateTime((Long) data[2]);
            file.setRemark((String) data[3]);
            file.setType((String) data[4]);
            items.add(file);
        }
        return items;
    }
}
