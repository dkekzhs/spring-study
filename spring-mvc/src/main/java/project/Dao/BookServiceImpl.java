package project.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookDao bookDao;

    @Override
    public String create(Map<String, Object> map) {
        int insert = this.bookDao.insert(map);
        if (insert == 1){
            return map.get("book_id").toString();
        }
        return null;
    }

    @Override
    public Map<String, Object> detail(Map<String, Object> map) {
        return this.bookDao.selectDetail(map);
    }

    @Override
    public List<Map<String, Object>> selectList(Map<String, Object> map) {
        List<Map<String, Object>> maps = this.bookDao.selectList(map);
        return maps;
    }

    @Override
    public boolean delete(Map<String, Object> map) {
        int delete = this.bookDao.delete(map);
        return delete == 1;
    }

    @Override
    public boolean update(Map<String, Object> map) {
        int update = this.bookDao.update(map);

        return update == 1;
    }
}
