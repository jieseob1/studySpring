package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    //Model 에 관련된 클래스
    private  String viewName; //논리 뷰
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) { // 생성자로 view의 이름만 넣으면 된다.
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
