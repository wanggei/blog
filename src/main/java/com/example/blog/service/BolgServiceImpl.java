package com.example.blog.service;

import com.example.blog.dao.*;
import com.example.blog.model.Blog;
import com.example.blog.model.Tages;
import com.example.blog.unitl.BlogUntil;
import com.example.blog.unitl.TageUntis;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("blogService")
public class BolgServiceImpl extends BaseServiceImpl<Blog> implements BlogService {

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private BlogUserDao blogUserDao;
    @Autowired
    private BlogTagesDao blogTagesDao;
    @Autowired
    private BlogTypeDao blogTypeDao;
    @Override
    public BaseDao baseDao() {
        return baseDao;
    }
    @Override
    public void insert(Blog blog,Integer uid) {
        blog.setFlag("是");
        blog.setViews(0);
         this.addForNotMath(new Object[]{"id","title","content","firstpicture","flag","views","appreciation","Opencomment","sharestatment","publish","recommend","createtime","blogdescribe"}
         ,new Object[]{null,blog.getTitle(),blog.getContent(),blog.getFirstpicture(),blog.getFlag(),
              blog.getViews(),blog.isAppreciation(),blog.isOpencomment(),blog.isSharestatment(),blog.isPublish(),blog.isRecommend(),blog.getCreatetime(),blog.getBlogdescribe()});

        Blog blog1= blogDao.getBlogByTitle(blog.getTitle());
        //插入标签博客表
        List<Tages> tages = blog.getTages();
        for (Tages tage:tages){
            blogTagesDao.addForNotMath("t_blog_tages",new Object[]{"id","tid","bid"},new Object[]{null,tage.getId(),blog1.getId()});
        }
        //插入博客分类表
        blogTypeDao.addForNotMath("t_blog_type",new Object[]{"id","bid","tyid"},new Object[]{null,blog1.getId(),blog.getTypes().getId()});
        //插入用户博客表
        blogUserDao.addForNotMath("t_user_blog",new Object[]{"id","uid","bid"},new Object[]{null,uid,blog1.getId()});
    }

    @Override
    public List<Blog> getAllById(Integer id) {
        return blogDao.getAllById(id);
    }


    @Override
    public PageInfo<Blog> getblogPage(Integer id,Integer PageNum, Integer PageSize) {
        Page<Blog> pageHelper=PageHelper.startPage(PageNum,PageSize);
        List<Blog> all = blogDao.getAllById(id);
        PageInfo<Blog> pageInfo=new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public void deleteByid(Integer bid) {
        blogTypeDao.deleteById(bid);
        blogTagesDao.deleteById(bid);
        blogUserDao.deleteById(bid);
        this.delete(bid);
    }

    @Override
    public PageInfo getSearchByTitle(String title ,Integer PageNum,Integer PageSize) {
        Page<Blog> objects = PageHelper.startPage(PageNum, PageSize);
        List<Blog> searchByTitle = blogDao.getSearchByTitle(title);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(searchByTitle);
        return blogPageInfo;
    }

    @Override
    public Blog getOne(Integer id) {
        return blogDao.getOne(id);
    }

    @Override
    public int upDateBlog(Blog blog) {
        int i=1;
        blog.setUpdatetime(new Timestamp(new Date().getTime()));
        this.updateBlog(blog);
        blogTagesDao.deleteById(blog.getId());
        blogTypeDao.deleteById(blog.getId());
        //插入标签博客表
        List<Tages> tages = blog.getTages();
        for (Tages tage:tages){
            blogTagesDao.addForNotMath("t_blog_tages",new Object[]{"id","tid","bid"},new Object[]{null,tage.getId(),blog.getId()});
        }
        //插入博客分类表
        blogTypeDao.addForNotMath("t_blog_type",new Object[]{"id","bid","tyid"},new Object[]{null,blog.getId(),blog.getTypes().getId()});
        return i;
    }


    @Override
    public PageInfo<Blog> getAllFinally(Integer PageNum,Integer PageSize) {
        Page<Blog> page=PageHelper.startPage(PageNum,PageSize);
        List<Blog> all = blogDao.getAll();
        PageInfo<Blog> pageInfo=new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public Blog getOneById(Integer id) {
        return blogDao.getOneById(id);
    }

    @Override
    public List<String> getYear() {
        return blogDao.getYear();
    }

    @Override
    public List<Blog> getByYear(String year) {
        return blogDao.getByYear(year);
    }

    @Override
    public int updateViews(Integer id) {
        return blogDao.updateViews(id);
    }

    @Override
    public List<Blog> getBycreatimeDesc() {
        return blogDao.getBycreatimeDesc();
    }

    @Override
    public PageInfo<Blog> getByTage(String tagename,Integer PageNum,Integer PageSize) {
        Page page=PageHelper.startPage(PageNum,PageSize);
        List<Blog> byTage = blogDao.getByTage(tagename);
        PageInfo<Blog> pageInfo=new PageInfo<>(byTage);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> getByType(String typeName, Integer pageNum, Integer pageSize) {
        Page page=PageHelper.startPage(pageNum,pageSize);
        List<Blog> byTage = blogDao.getByType(typeName);
        PageInfo<Blog> pageInfo=new PageInfo<>(byTage);
        return pageInfo;
    }
}
