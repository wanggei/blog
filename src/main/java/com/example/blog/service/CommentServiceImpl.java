package com.example.blog.service;

import com.example.blog.dao.BaseDao;
import com.example.blog.dao.CommentDao;
import com.example.blog.model.Comment;
import com.sun.javafx.robot.impl.BaseFXRobot;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private CommentDao commentDao;
    @Override
    public BaseDao baseDao() {
        return baseDao;
    }


    @Override
    public List<Comment> getAllComment(Integer bid) {
        List<Comment> allList = commentDao.getAllList(bid);
        return eachComment(allList);
    }

    @Override
    public void addComment(Comment comment) {
        int id = comment.getParentCommment().getId();
        if(id !=-1){
            comment.setCreatTime(new Date());
            comment.setParentCommment(commentDao.getone(id));
            this.addForNotMath(new Object[]{"id","nickename","email","content","avator","creattime","blog_id","parentcomment_id"},new Object[]{
                    null,comment.getNickename(),comment.getEmail(),comment.getContent(),comment.getAvator(),comment.getCreatTime(),
                    comment.getBlog().getId(),comment.getParentCommment().getId()
            });
        }else{
            comment.setCreatTime(new Date());
            comment.setParentCommment(null);
            this.addForNotMath(new Object[]{"id","nickename","email","content","avator","creattime","blog_id",},new Object[]{
                            null,comment.getNickename(),comment.getEmail(),comment.getContent(),comment.getAvator(),comment.getCreatTime(),
                            comment.getBlog().getId()
            });
        }

    }


    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentList=new ArrayList<>();
        for (Comment comment: comments){
            Comment comment1=new Comment();
            BeanUtils.copyProperties(comment,comment1);
            commentList.add(comment1);
        }
        combineChildren(commentList);
        return commentList;
    }

    public void combineChildren(List<Comment> comments){

        for (Comment comment:comments){
            List<Comment> commentList = commentDao.getoneByparentId(comment.getId());
            if (commentList.size()>0){
                comment.setReplyComment(commentList);
                List<Comment> replys=comment.getReplyComment();
                for (Comment rplysl :replys){
                    recursively(rplysl);
                }
            }
              comment.setReplyComment(tempRplsy);
              tempRplsy=new ArrayList<>();
        }
    }
    private List<Comment> tempRplsy=new ArrayList<>();

    private void recursively(Comment comment){
        tempRplsy.add(comment);
        if(comment.getReplyComment()!=null){
            List<Comment> commentList=comment.getReplyComment();
            for ( Comment comment1:commentList){
                tempRplsy.add(comment1);
                if (comment.getReplyComment()!=null){
                    recursively(comment1);
                }
            }
        }

    }
}
