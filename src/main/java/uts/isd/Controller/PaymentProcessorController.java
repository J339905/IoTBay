// public class PaymentProcessorController {

// package uts.isd.controller;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.List;
// import javax.servlet.RequestDispatcher;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import uts.isd.model.PaymentInformation;
// import uts.isd.model.Customer;
// import uts.isd.model.User;
// import uts.isd.model.dao.DBPaymentInformation;
// import uts.isd.model.dao.DBAuditLogs;
// import uts.isd.model.dao.IPaymentInformation;
// import uts.isd.util.Flash;
// import uts.isd.util.Logging;
// import uts.isd.util.URL;
// import uts.isd.validation.Validator;
// import uts.isd.validation.ValidatorFieldRules;
 
// public class PaymentProcessorController extends HttpServlet {
    
//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
        
//         if (request.getPathInfo() == null || request.getPathInfo().equals("/"))
//         {
//             doListPaymentInformationGet(request, response, "");
//             return;
//         }
        
//         Logging.logMessage("** Path Info is: "+request.getPathInfo());
//         String[] segments = request.getPathInfo().split("/");
        

        
//         switch (segments[1])
//         {
//             case "list":
//                 doListPaymentInformationGet(request, response, (segments.length == 3 ? segments[2] : ""));
//                 break;
                
//             case "add":
//                 doAddPaymentInformationGet(request, response);
//                 break;
                
//             case "edit":
//                 doUpdatePaymentInformationGet(request, response, (segments.length == 3 ? segments[2] : ""));
//                 break;
                
//             case "delete":
//                 doDeletePaymentInformationGet(request, response, (segments.length == 3 ? segments[2] : ""));
//                 break;
                
//             case "view":
//                 doViewPaymentInformationGet(request, response, (segments.length == 3 ? segments[2] : ""));
//                 break;
                
//         }
//     }

//     protected void doListPaymentInformationGet(HttpServletRequest request, HttpServletResponse response, String customerIdStr)
//             throws ServletException, IOException 
//     {
//         Flash flash = Flash.getInstance(request.getSession());
//         try
//         {
//             User user = (User)request.getSession().getAttribute("user");
//             if (user == null)
//             {
//                 flash.add(Flash.MessageType.Error, "You are not logged in");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             int customerId = user.getCustomerId(); 
            
//             if (user.isAdmin() && !customerIdStr.isEmpty())
//             {
//                 customerId = Integer.parseInt(customerIdStr);
//             }
            
//             IPaymentInformation dbPaymentInformation = new DBPaymentInformation();
//             List<PaymentInformation> paymentInformation = dbPaymentInformation.getAllPaymentInformationByCustomerId(customerId);
//             request.setAttribute("paymentInformation", paymentInformation);
//         } 
//         catch (Exception e) 
//         {
//             flash.add(Flash.MessageType.Error, "Unable to list payment information");
//             Logging.logMessage("Unable to get payment information");
//             URL.GoBack(request, response);
//             return;
//         }
        
//         RequestDispatcher requestDispatcher; 
//         requestDispatcher = request.getRequestDispatcher("/view/paymentprocessor/list.jsp");
//         requestDispatcher.forward(request, response); 
//     }
    
//     protected void doAddPaymentInformationGet(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException 
//     {
//         Flash flash = Flash.getInstance(request.getSession());
//         User user = (User)request.getSession().getAttribute("user");
        
//         if (user == null)
//         {
//             flash.add(Flash.MessageType.Error, "You are not logged in");
//             URL.GoBack(request, response);
//             return;
//         }
            
//         RequestDispatcher requestDispatcher; 
//         requestDispatcher = request.getRequestDispatcher("/view/paymentprocessor/add.jsp");
//         requestDispatcher.forward(request, response); 
//     }
    
//     protected void doUpdatePaymentInformationGet(HttpServletRequest request, HttpServletResponse response, String paymentInformationStr)
//             throws ServletException, IOException 
//     {
//         Flash flash = Flash.getInstance(request.getSession());
//         try
//         {
//             User user = (User)request.getSession().getAttribute("user");
//             if (user == null)
//             {
//                 flash.add(Flash.MessageType.Error, "You are not logged in");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             int paymentInformationId = Integer.parseInt(paymentInformationStr);
            
//             IPaymentInformation dbPaymentInformation = new DBPaymentInformation();
//             PaymentInformation paymentInformation = dbPaymentInformation.getPaymentInformationById(paymentInformationId);
            
//             if (paymentInformation == null)
//             {
//                 flash.add(Flash.MessageType.Error, "Unable to find payment information to edit");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             if ((paymentInformation.getCustomerId() != user.getCustomerId()) && !user.isAdmin())
//             {
//                 flash.add(Flash.MessageType.Error, "Access denied");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             request.setAttribute("paymentInformation", paymentInformation);
            
//             RequestDispatcher requestDispatcher; 
//             requestDispatcher = request.getRequestDispatcher("/view/paymentprocessor/edit.jsp");
//             requestDispatcher.forward(request, response); 
//         } 
//         catch (Exception e) 
//         {
//             flash.add(Flash.MessageType.Error, "Unable to edit payment information");
//             Logging.logMessage("Unable to edit payment information");
//             URL.GoBack(request, response);
//             return;
//         }
//     }
    
//     protected void doDeletePaymentInformationGet(HttpServletRequest request, HttpServletResponse response, String paymentInformationStr)
//             throws ServletException, IOException 
//     {
//         Flash flash = Flash.getInstance(request.getSession());
//         try
//         {
//             User user = (User)request.getSession().getAttribute("user");
//             if (user == null)
//             {
//                 flash.add(Flash.MessageType.Error, "You are not logged in");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             int paymentInformationId = Integer.parseInt(paymentInformationStr);
            
//             IPaymentInformation dbPaymentInformation = new DBPaymentInformation();
//             PaymentInformation paymentInformation = dbPaymentInformation.getPaymentInformationById(paymentInformationId);
            
//             if (paymentInformation == null)
//             {
//                 flash.add(Flash.MessageType.Error, "Unable to find payment information to delete");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             request.setAttribute("paymentInformation", paymentInformation);
            
//             RequestDispatcher requestDispatcher; 
//             requestDispatcher = request.getRequestDispatcher("/view/paymentprocessor/delete.jsp");
//             requestDispatcher.forward(request, response); 
//         } 
//         catch (Exception e) 
//         {
//             flash.add(Flash.MessageType.Error, "Unable to delete payment information");
//             Logging.logMessage("Unable to delete payment information");
//             URL.GoBack(request, response);
//             return;
//         }
//     }
    
    
//     protected void doViewPaymentInformationGet(HttpServletRequest request, HttpServletResponse response, String paymentInformationStr)
//             throws ServletException, IOException 
//     {
//         Flash flash = Flash.getInstance(request.getSession());
//         try
//         {
//             User user = (User)request.getSession().getAttribute("user");
//             if (user == null)
//             {
//                 flash.add(Flash.MessageType.Error, "You are not logged in");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             int paymentInformationId = Integer.parseInt(paymentInformationStr);
            
//             IPaymentInformation dbPaymentInformation = new DBPaymentInformation();
//             PaymentInformation paymentInformation = dbPaymentInformation.getPaymentInformationById(paymentInformationId);
            
//             if (paymentInformation == null)
//             {
//                 flash.add(Flash.MessageType.Error, "Unable to find payment information to view");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             request.setAttribute("paymentInformation", paymentInformation);
            
//             RequestDispatcher requestDispatcher; 
//             requestDispatcher = request.getRequestDispatcher("/view/paymentprocessor/view.jsp");
//             requestDispatcher.forward(request, response); 
//         } 
//         catch (Exception e) 
//         {
//             flash.add(Flash.MessageType.Error, "Unable to view payment information");
//             Logging.logMessage("Unable to view payment information");
//             URL.GoBack(request, response);
//             return;
//         }
//     }
    
    
//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException
//     {
//         Logging.logMessage("** Path Info is: "+request.getPathInfo());
//         String[] segments = request.getPathInfo().split("/");
        
//         switch (segments[1])
//         {
                
//             case "add":
//                 doAddPaymentInformationPost(request, response);
//                 break;
                
//             case "edit":
//                 doUpdatePaymentInformationPost(request, response, (segments.length == 3 ? segments[2] : ""));
//                 break;
                
//             case "delete":
//                 doDeletePaymentInformationPost(request, response, (segments.length == 3 ? segments[2] : ""));
//                 break;
                
//         }
//     }
    
//     protected void doAddPaymentInformationPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException 
//     {
//         HttpSession session = request.getSession();
//         Flash flash = Flash.getInstance(session);
//         User user = (User)session.getAttribute("user");
//         Customer customer = (Customer)session.getAttribute("customer");
//         boolean isLoggedIn = (customer != null && user != null);
        
//         try
//         {
//             if (!isLoggedIn)
//             {
//                 flash.add(Flash.MessageType.Error, "You are not logged in");
//                 URL.GoBack(request, response);
//                 return;
//             }

//             Validator validator = new Validator(new ValidatorFieldRules[] {
//                  new ValidatorFieldRules("Default Payment", "defaultPayment", "trim"),
//                  new ValidatorFieldRules("Payment Type", "paymentType", "required|integer"), 
//                  new ValidatorFieldRules("Card Name", "cardName", "required"),
//                  new ValidatorFieldRules("Card Number", "cardNumber", "required|integer|longerthan[7]|shorterthan[20]"),
//                  new ValidatorFieldRules("Card CVV", "cardCVV", "required|integer|longerthan[2]|shorterthan[4]"),
//                  new ValidatorFieldRules("Card Expiry", "cardExpiry", "required|integer|shorterthan[5]|longerthan[3]")
//             });

//             if (!validator.validate(request))
//             {
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             IPaymentInformation dbPaymentInformation = new DBPaymentInformation();
//             PaymentInformation paymentInformation = new PaymentInformation();
//             paymentInformation.loadRequest(request);
            
//             if (customer != null)
//                 paymentInformation.setCustomerId(customer.getId());
            
//             if (user != null)
//                 paymentInformation.setUserId(user.getId());
            
//             if (dbPaymentInformation.addPaymentInformation(paymentInformation, customer))
//             {
//                 DBAuditLogs.addEntry(DBAuditLogs.Entity.PaymentInformation, "Added", "Added payment information", customer.getId());
//                 flash.add(Flash.MessageType.Success, "New payment information added successfully");
//                 response.sendRedirect(URL.Absolute("paymentinformation/list", request));
//                 return;
//             }
//             else
//             {
//                 flash.add(Flash.MessageType.Error, "Failed to add new payment information");
//                 RequestDispatcher requestDispatcher; 
//                 requestDispatcher = request.getRequestDispatcher("/view/paymentprocessor/add.jsp");
//                 requestDispatcher.forward(request, response); 
//             }
//         }
//         catch (Exception e)
//         {
//             Logging.logMessage("Unable to add payment information", e);
//             flash.add(Flash.MessageType.Error, "Unable to add payment information");
//             URL.GoBack(request, response);
//             return;
//         }
//     }
    
//     protected void doUpdatePaymentInformationPost(HttpServletRequest request, HttpServletResponse response, String paymentInformationStr)
//             throws ServletException, IOException 
//     {
//         HttpSession session = request.getSession();
//         Flash flash = Flash.getInstance(session);
//         User user = (User)session.getAttribute("user");
//         Customer customer = (Customer)session.getAttribute("customer");
//         boolean isLoggedIn = (customer != null && user != null);
        
//         try
//         {
//             if (!isLoggedIn)
//             {
//                 flash.add(Flash.MessageType.Error, "You are not logged in");
//                 URL.GoBack(request, response);
//                 return;
//             }

//             Validator validator = new Validator(new ValidatorFieldRules[] {
//                  new ValidatorFieldRules("Default Payment", "defaultPayment", "trim"),
//                  new ValidatorFieldRules("Payment Type", "paymentType", "required|integer"), 
//                  new ValidatorFieldRules("Card Name", "cardName", "required"),
//                  new ValidatorFieldRules("Card Number", "cardNumber", "required|integer|longerthan[7]|shorterthan[20]"),
//                  new ValidatorFieldRules("Card CVV", "cardCVV", "required|integer|longerthan[2]|shorterthan[4]"),
//                  new ValidatorFieldRules("Card Expiry", "cardExpiry", "required|integer|shorterthan[5]|longerthan[3]")
//             });

//             if (!validator.validate(request))
//             {
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             IPaymentInformation dbPaymentInformation = new DBPaymentInformation();
            
//             int paymentInformationId = Integer.parseInt(paymentInformationStr);
//             PaymentInformation paymentInformation = dbPaymentInformation.getPaymentInformationById(paymentInformationId);
            
//             if (paymentInformation == null)
//             {
//                 flash.add(Flash.MessageType.Error, "Unable to find payment information to edit");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             if ((paymentInformation.getCustomerId() != user.getCustomerId()) && !user.isAdmin())
//             {
//                 flash.add(Flash.MessageType.Error, "Access denied");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             paymentInformation.loadRequest(request);
            
//             if (dbPaymentInformation.updatePaymentInformation(paymentInformation, customer))
//             {
//                 DBAuditLogs.addEntry(DBAuditLogs.Entity.PaymentInformation, "Updated", "Updated payment information", customer.getId());
//                 flash.add(Flash.MessageType.Success, "Existing payment information updated successfully");
//                 response.sendRedirect(URL.Absolute("paymentinformation/list", request));
//                 return;
//             }
//             else
//             {
//                 flash.add(Flash.MessageType.Error, "Failed to update payment information");
//                 RequestDispatcher requestDispatcher; 
//                 requestDispatcher = request.getRequestDispatcher("/view/paymentprocessor/edit.jsp");
//                 requestDispatcher.forward(request, response); 
//             }
//         }
//         catch (Exception e)
//         {
//             Logging.logMessage("Unable to update payment information", e);
//             flash.add(Flash.MessageType.Error, "Unable to update payment information");
//             URL.GoBack(request, response);
//             return;
//         }
//     }
    
//     protected void doDeletePaymentInformationPost(HttpServletRequest request, HttpServletResponse response, String paymentInformationStr)
//             throws ServletException, IOException 
//     {
//         HttpSession session = request.getSession();
//         Flash flash = Flash.getInstance(session);
//         User user = (User)session.getAttribute("user");
//         Customer customer = (Customer)session.getAttribute("customer");
//         boolean isLoggedIn = (customer != null && user != null);
        
//         try
//         {
//             if (!isLoggedIn)
//             {
//                 flash.add(Flash.MessageType.Error, "You are not logged in");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             IPaymentInformation dbPaymentInformation = new DBPaymentInformation();
            
//             int paymentInformationId = Integer.parseInt(paymentInformationStr);
//             PaymentInformation paymentInformation = dbPaymentInformation.getPaymentInformationById(paymentInformationId);
            
//             if (paymentInformation == null)
//             {
//                 flash.add(Flash.MessageType.Error, "Unable to find payment information to delete");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             if ((paymentInformation.getCustomerId() != user.getCustomerId()) && !user.isAdmin())
//             {
//                 flash.add(Flash.MessageType.Error, "Access denied");
//                 URL.GoBack(request, response);
//                 return;
//             }
            
//             if (dbPaymentInformation.deletePaymentInformation(paymentInformation))
//             {
//                 DBAuditLogs.addEntry(DBAuditLogs.Entity.PaymentInformation, "Deleted", "Deleted payment information", customer.getId());
//                 flash.add(Flash.MessageType.Success, "Payment information deleted successfully");
//                 response.sendRedirect(URL.Absolute("paymentinformation/list", request));
//                 return;
//             }
//             else
//             {
//                 flash.add(Flash.MessageType.Error, "Failed to delete payment information");
//                 RequestDispatcher requestDispatcher; 
//                 requestDispatcher = request.getRequestDispatcher("/view/paymentprocessor/delete.jsp");
//                 requestDispatcher.forward(request, response); 
//             }
//         }
//         catch (Exception e)
//         {
//             Logging.logMessage("Unable to delete payment information", e);
//             flash.add(Flash.MessageType.Error, "Unable to delete payment information");
//             URL.GoBack(request, response);
//             return;
//         }
//     }
    
// }
