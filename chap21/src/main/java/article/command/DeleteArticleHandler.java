package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.PermissionDeniedException;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

    private DeleteArticleService deleteService = new DeleteArticleService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1. 현재 로그인한 사용자 정보와 삭제할 글 번호를 가져옵니다.
        User authUser = (User) req.getSession().getAttribute("authUser");
        String noVal = req.getParameter("no");
        int no = Integer.parseInt(noVal);

        try {
            // 2. 서비스(주방장)에게 삭제를 지시합니다.
            deleteService.delete(no, authUser.getId());

            // 3. 삭제가 완료되면 게시글 목록 첫 페이지로 강제로 이동(Redirect) 시킵니다.
            res.sendRedirect(req.getContextPath() + "/article/list.do");
            return null; // 직접 이동시켰으므로 보여줄 JSP 경로를 반환하지 않습니다(null).

        } catch (ArticleNotFoundException e) {
            // 글이 이미 지워졌거나 없는 경우 (404 에러)
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } catch (PermissionDeniedException e) {
            // 본인이 작성한 글이 아닌 경우 (403 권한 없음 에러)
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }
}