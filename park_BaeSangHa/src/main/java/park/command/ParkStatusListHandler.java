package park.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import park.model.Park;
import park.service.ParkInOutService;

public class ParkStatusListHandler implements CommandHandler {

    private static final String LIST_VIEW = "/WEB-INF/view/parkStatusList.jsp";
    private ParkInOutService parkService = new ParkInOutService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 전체 주차 현황 목록 데이터 가져오기
        List<Park> parkList = parkService.getParkStatusList();

        req.setAttribute("parkList", parkList);

        req.setAttribute("viewPage", LIST_VIEW);
        return "/layout.jsp";
    }
}