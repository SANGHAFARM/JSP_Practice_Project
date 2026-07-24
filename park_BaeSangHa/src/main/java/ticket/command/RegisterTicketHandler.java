package ticket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import ticket.model.Ticket;
import ticket.service.RegisterTicketService;

public class RegisterTicketHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/registerTicketForm.jsp";
    private static final String SUCCESS_VIEW = "/WEB-INF/view/registerTicketSuccess.jsp";
    private RegisterTicketService registerService = new RegisterTicketService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        int nextTno = registerService.getNextTnoForDisplay();

        req.setAttribute("nextTno", nextTno);

        // section 영역에 들어갈 페이지 지정
        req.setAttribute("viewPage", FORM_VIEW);

        return "/layout.jsp";
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            int tno = Integer.parseInt(req.getParameter("tno"));
            String carno = req.getParameter("carno");
            String phone = req.getParameter("phone");
            String grade = req.getParameter("grade");
            
            // 기본값 'Y'로 설정
            String tstat = "Y";

            Ticket ticket = new Ticket(tno, carno, phone, grade, tstat);
            registerService.register(ticket);

            return SUCCESS_VIEW;

        } catch (Exception e) {
            req.setAttribute("dbError", "등록 중 오류가 발생했습니다.");

            // 에러 발생 시 번호를 다시 조회하고 뷰를 유지
            int nextTno = registerService.getNextTnoForDisplay();
            req.setAttribute("nextTno", nextTno);
            req.setAttribute("viewPage", FORM_VIEW);

            return "/layout.jsp";
        }
    }
}