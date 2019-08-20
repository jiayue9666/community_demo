package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private User user;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {


        pages.clear();
        if(totalCount%size==0){
            this.totalPage =totalCount/size;
        }else{
            this.totalPage =totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>this.totalPage){
            page=this.totalPage;
        }
        this.page=page;
        if (page==1){
            for(int i=0;i<4;i++)
                if(page + i<=this.totalPage) {
                    pages.add( page + i);
                }
        }
        if (page==2){
            for(int i=0;i<page+3;i++){
                if(page-1+i<=this.totalPage){
                    pages.add(page-1+i);
                }

            }
        }
        if (page==3){
            for(int i=0;i<page+3;i++){
                if(page - 2 + i<=this.totalPage) {
                    pages.add(page - 2 + i);
                }
            }
        }
        if(page>3){
            for (int i=0;i<page+3;i++){
                if(page - 3 + i<=this.totalPage) {
                    pages.add(page - 3 + i);
                }
            }
        }


        //是否展示上一页
        if (page==1){
            showFirstPage=false;
        }
        else {
            showPrevious=true;
        }
        //是否展示下一页
        if (page==this.totalPage){
            showNext=false;
        }
        else showNext=true;

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else showFirstPage=true;
        //是否展示最后一页
        if(pages.contains(this.totalPage)){
            showEndPage=false;
        }else showEndPage=true;
    }
}
