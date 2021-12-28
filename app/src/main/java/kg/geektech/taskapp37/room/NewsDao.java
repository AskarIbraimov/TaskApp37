package kg.geektech.taskapp37.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kg.geektech.taskapp37.models.News;

@Dao
public interface NewsDao {

    //достает объект с базы данных
    @Query("SELECT * FROM news order by createdAt Desc")
    List<News> getAll();

    @Query("SELECT * FROM news order by title ASC ")
    List<News> getAllSortedByTitle();

    //вставляет объект в базу данных
    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Update
    void update(News news);
}
