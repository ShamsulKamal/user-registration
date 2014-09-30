package com.struts.registration.web.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import com.struts.registration.dao.UserDao;
import com.struts.registration.domain.User;
import com.struts.registration.domain.UserProperties;
import com.struts.registration.utils.DaoFactoryUtil;

public class DownloadDocumentAction extends DownloadAction {

    @Override
    protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        String id = request.getParameter(UserProperties.ID);
        String uuid = request.getParameter(UserProperties.UUID);

        UserDao userDao = DaoFactoryUtil.getInstance().getDaoFactory().getUserDao();
        User user = userDao.findByIdAndUuid(Long.valueOf(id), uuid);

        String contentType = "text/plain";
        // Create a file
        File file = new File(user.getDocumentPath());
        // Return FileStreamInfo by passing contentType and file
        response.setContentType(contentType);
        // FIXME: filename
        response.setHeader("Content-disposition", "attachment; filename=" + "download_document.txt");
        return new FileStreamInfo(contentType, file);
    }

}
