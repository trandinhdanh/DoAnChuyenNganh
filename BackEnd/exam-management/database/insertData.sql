use exam_management;

INSERT INTO user(user_name,role,status,password)
VALUES
    ('admin','ADMIN','ACTIVE','$2a$10$TMkrd3LKZDtJpXnbRZ8oKOxTvYc0MSUymS0e07T9yjVUAefYEBRpC'),
    ('pvtinh','TEACHER','ACTIVE','$2a$10$TMkrd3LKZDtJpXnbRZ8oKOxTvYc0MSUymS0e07T9yjVUAefYEBRpC'),
    ('nvdu','TEACHER','ACTIVE','$2a$10$TMkrd3LKZDtJpXnbRZ8oKOxTvYc0MSUymS0e07T9yjVUAefYEBRpC'),
    ('tttnga','TEACHER','ACTIVE','$2a$10$TMkrd3LKZDtJpXnbRZ8oKOxTvYc0MSUymS0e07T9yjVUAefYEBRpC'),
    ('lphung','TEACHER','ACTIVE','$2a$10$TMkrd3LKZDtJpXnbRZ8oKOxTvYc0MSUymS0e07T9yjVUAefYEBRpC'),
    ('nguyenvanhieu','STUDENT','ACTIVE','$2a$10$A9mMlX81dmj.DbALw8/5TOJ09NMkMlz.xGVEYQyUaWWz5nDIpt3CW'),
    ('trandinhdanh','STUDENT','ACTIVE','$2a$10$mrzRE.UegezT2QpR9FeG0eMiQT1kkGvFlkzJq2nA.1XFWqkpxwW/i'),
    ('nguyentruonggiang','STUDENT','ACTIVE','$2a$10$YaVh0sBWlBCdMjDMmfpb0OckXzgZVyOaAZK0mlsmI7LKDK7xN9W5K'),
    ('hoangthanhtai','STUDENT','ACTIVE','$2a$10$2FY1sOZ391FXFs/LWVeEY.EDkBdQ9OPDtcGO9qnR8BhXtu6JS4dXK'),
    ('nguyenhoangduc','STUDENT','ACTIVE','$2a$10$UK7cOcL2.6JEYtzQTWpsDOpkvXo4AWOPdKTrsSUqh4xr7JTZ6wfpK'),
    ('phamcongdanh','STUDENT','ACTIVE','$2a$10$pABlyO8eXhlFfJFVw1l7rOIMvL34f5y8GM8N7HwXLWr/dzYqJjo5K');

INSERT INTO teacher(full_name,gender,birthday,user_id,position)
VALUES
    ('Phạm Văn Tính','NAM','1990-01-01',2,'Giảng viên'),
    ('Nguyễn Văn Dũ','NAM','1990-01-01',3,'Giảng viên'),
    ('Trần Thị Thanh Nga','NỮ','1990-01-01',4,'Giảng viên'),
    ('Lê Phi Hùng','NAM','1990-01-01',5,'Giảng viên');

INSERT INTO student(full_name,gender,birthday,user_id)
VALUES
    ('Nguyễn Văn Hiếu','NAM','2001-04-27',6),
    ('Trần Đình Danh','NAM','2001-03-18',7),
    ('Nguyễn Trường Giang','NAM','2001-01-28',8),
    ('Hoàng Thanh Tài','NAM','2001-07-21',9),
    ('Nguyễn Hoàng Đức','NAM','2001-10-31',10),
    ('Phạm Công Danh','NAM','2001-02-20',11);

INSERT INTO course(name,teacher_id)
VALUES
    ('Chuyên đề java',1),
    ('Đồ án chuyên ngành',2),
    ('Thiết kế hướng đối tượng',3),
    ('Chuyên đề web',4);

INSERT INTO course_student(course_id,student_id)
VALUES
    (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),
    (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),
    (3,1),(3,2),(3,5),
    (4,1),(4,2),(4,3),(4,4),(4,5),(4,6);

INSERT INTO exam(name,course_id)
VALUES
    ('Thi giữa kì',1),
    ('Thi cuối kì',1),
    ('Bài test Scrum',2),
    ('Thi giữa kì lần 1',3),
    ('Thi giữa kì lần 2',3),
    ('Bài tập về Spring',4),
    ('Thi giữa kì',4);

INSERT INTO result(score,student_id,exam_id,is_complete)
VALUES
    (0,1,1,0),(0,1,2,0),(0,1,3,0),(0,1,4,0),(0,1,5,0),(0,1,6,0),(0,1,7,0),
    (0,2,1,0),(0,2,2,0),(0,2,3,0),(0,2,4,0),(0,2,5,0),(0,2,6,0),(0,2,7,0),
    (0,3,1,0),(0,3,2,0),(0,3,3,0),(0,3,6,0),(0,3,7,0),
    (0,4,1,0),(0,4,2,0),(0,4,3,0),(0,4,6,0),(0,4,7,0),
    (0,5,1,0),(0,5,2,0),(0,5,3,0),(0,5,4,0),(0,5,5,0),(0,5,6,0),(0,5,7,0),
    (0,6,1,0),(0,6,2,0),(0,6,3,0),(0,6,6,0),(0,6,7,0);

INSERT INTO question(question,exam_id)
VALUES
    ('Khai báo phương thức chính nào dưới đây sẽ cho phép một lớp được bắt đầu như một chương trình độc lập',1),
    ('Đâu là câu SAI về ngôn ngữ Java?',1),
    ('Đâu không phải là một kiểu dữ liệu nguyên thủy trong Java?',1),
    ('Đâu không phải là một kiểu dữ liệu nguyên thủy trong Java?',1),
    ('Phương thức next() của lớp Scanner dùng để làm gì?',1),
    ('Muốn chạy được chương trình java, chỉ cần cài phần mền nào sau đây?',1),
    ('Gói nào trong java chứa lớp Scanner dùng để nhập dữ liệu từ bàn phím?',1),
    ('Phương thức nextLine() thuộc lớp nào ?',1),
    ('Tên đầu tiên của Java là gì?',1),
    ('G/s đã định nghĩa lớp XX với một phương thức thông thường là Display, sau đó sinh ra đối tượng objX từ lớp XX. Để gọi phương thức Display ta sử dụng cú pháp nào?',1),

    ('Đâu KHÔNG phải là thành phần trong cấu trúc của lớp trong java?',2),
    ('Hàm tạo được sử dụng để hủy đối tượng',2),
    ('Với giá trị nào của x, biểu thức sau trả về giá trị true (x thuộc kiểu int). x % 3 == 0?',2),
    ('Lựa chọn đáp án phù hợp:',2),
    ('Kiểu dữ liệu nào trong Java chứa giá trị bao gồm cả chữ và số?',2),
    ('Đâu là khai báo biến hợp lệ?',2),
    ('Biểu thức nào có giá trị khác các biểu thức còn lại trong các biểu thức sau? Cho x=true thuộc kiểu boolean.',2),
    ('Có mấy cách để truyền tham số vào cho một phương thức? Chọn một câu trả lời',2),
    ('Trong các khai báo sau, đâu là khai báo không hợp lệ? Chọn một câu trả lời',2),
    ('Phát biểu nào sau đây là đúng? Chọn một câu trả lời',2),

    ('Scrum Framework là gì?', 3),
    ('Scrum Master trong Scrum Framework có nhiệm vụ gì?', 3),
    ('Scrum Team gồm những thành viên nào?', 3),
    ('User Story trong Scrum Framework là gì?', 3),
    ('Các cuộc họp hàng tuần trong Scrum Framework gồm những cuộc họp nào?', 3),
    ('Sprint là gì trong Scrum Framework?', 3),
    ('Scrum Board trong Scrum Framework được sử dụng để làm gì?', 3),
    ('Sprint Review là cuộc họp dùng để làm gì?', 3),
    ('Đội Scrum (Scrum Team) có thể tự tổ chức như thế nào trong Scrum Framework?', 3),
    ('Product Owner trong Scrum Framework có vai trò gì?', 3),

    ('Trong Java, từ khóa nào được sử dụng để tạo một đối tượng mới từ một lớp?', 4),
    ('Từ khóa nào được sử dụng để kế thừa một lớp trong Java?', 4),
    ('Interface trong Java là gì?', 4),
    ('Từ khóa nào được sử dụng để chặn việc kế thừa một lớp trong Java?', 4),
    ('Trong Java, phương thức nào được gọi mặc định khi một đối tượng mới được tạo?', 4),
    ('Để truy cập vào một phương thức hoặc biến thành viên của một lớp trong Java, chúng ta sử dụng từ khóa nào?', 4),
    ('Trong Java, một lớp có thể triển khai nhiều interface.', 4),
    ('Trong lập trình OOP, tính đa hình (polymorphism) được định nghĩa như thế nào?', 4),
    ('Trong Java, từ khóa nào được sử dụng để truy cập vào phương thức hoặc biến thành viên của lớp cha?', 4),
    ('Trong Java, một lớp con có thể ghi đè (override) phương thức của lớp cha.', 4),

    ('Trong Java, từ khóa nào được sử dụng để xác định một biến hoặc phương thức là không thể thay đổi?', 5),
    ('Trong Java, tính đóng gói (encapsulation) được định nghĩa như thế nào?', 5),
    ('Trong Java, một lớp con có thể truy cập các phương thức và biến thành viên của lớp cha.', 5),
    ('Trong Java, từ khóa nào được sử dụng để tạo một lớp trừu tượng (abstract class)?', 5),
    ('Trong Java, ghi đè (overriding) và nạp chồng (overloading) có điểm khác biệt nhau là:', 5),
    ('Trong Java, từ khóa nào được sử dụng để đánh dấu một phương thức là trừu tượng và không có triển khai trong lớp hiện tại?', 5),
    ('Trong Java, một interface có thể kế thừa từ một interface khác.', 5),
    ('Trong lập trình OOP, khái niệm "đa kế thừa" (multiple inheritance) là gì?', 5),
    ('Trong Java, từ khóa nào được sử dụng để truy cập vào các thành phần của một lớp mà không cần tạo đối tượng của lớp đó?', 5),
    ('Trong Java, khái niệm "giao diện" (interface) được sử dụng để làm gì?', 5),

    ('',6),
    ('',6),
    ('',6),
    ('',6),
    ('',6),
    ('',6),
    ('',6),
    ('',6),
    ('',6),
    ('',6),

    ('',7),
    ('',7),
    ('',7),
    ('',7),
    ('',7),
    ('',7),
    ('',7),
    ('',7),
    ('',7),
    ('',7);

INSERT INTO answer(answer,question_id,correct_answer)
VALUES
    ('public static int main(char args[])',1,0),
    ('public static void main(String args[])',1,1),
    ('public static void MAIN(String args[])',1,0),
    ('public static void main(String args)',1,0),

    ('Ngôn ngữ Java có phân biệt chữ hoa – chữ thường', 2, 0),
    ('Java là ngôn ngữ lập trình hướng đối tượng', 2, 1),
    ('Dấu chấm phẩy được sử dụng để kết thúc lệnh trong java', 2, 0),
    ('Chương trình viết  bằng Java chỉ có thể chạy trên hệ điều hành win', 2, 0),

    ('double', 3, 0),
    ('int', 3, 0),
    ('long', 3, 0),
    ('long float', 3, 1),

    ('Tên của chương trình', 4, 0),
    ('Số lượng tham số', 4, 0),
    ('Tham số đầu tiên của danh sách tham số', 4, 1),
    ('Không câu nào đúng', 4, 0),

    ('Nhập một số nguyên', 5, 0),
    ('Nhập một ký tự', 5, 0),
    ('Nhập một chuỗi', 5, 1),
    ('Không có phương thức này', 5, 0),

    ('Netbeans', 6, 0),
    ('Eclipse', 6, 0),
    ('JDK', 6, 1),
    ('Java Platform', 6, 0),

    ('java.net', 7, 0),
    ('java.io', 7, 0),
    ('java.util', 7, 1),
    ('java.awt', 7, 0),

    ('String', 8, 0),
    ('Scanner', 8, 1),
    ('Integer', 8, 0),
    ('System', 8, 0),

    ('Java', 9, 0),
    ('Oak', 9, 0),
    ('Cafe', 9, 0),
    ('James Golings', 9, 1),

    ('XX.Display;', 10, 0),
    ('XX.Display();', 10, 1),
    ('objX.Display();', 10, 0),
    ('Display();',10,0) ,

    ('Tên lớp', 11, 0),
    ('Thuộc tính', 11, 0),
    ('Phương thức', 11, 0),
    ('Biến', 11, 1),

    ('Cả hai câu đều đúng', 12, 0),
    ('Cả hai câu đều sai', 12, 0),
    ('Câu 1 đúng, câu 2 sai', 12, 0),
    ('Câu 2 đúng, câu 1 sai', 12, 1),

    ('2', 13, 0),
    ('7', 13, 0),
    ('4', 13, 1),
    ('9', 13, 0),

    ('a-4, b-5, c-2, d-1, e-3', 14, 0),
    ('a-1, b-2, c-2, d-1, e-3', 14, 0),
    ('a-1, b-5, c-4, d-4, e-3', 14, 1),
    ('a-5, b-4, c-1, d-2, e-3', 14, 0),

    ('int', 15, 0),
    ('byte', 15, 0),
    ('char', 15, 1),
    ('String', 15, 0),

    ('theOne', 16, 1),
    ('the One', 16, 0),
    ('1the_One', 16, 0),
    ('$the One', 16, 0),

    ('true', 17, 0),
    ('x==true;', 17, 0),
    ('1==1', 17, 0),
    ('!x', 17, 1),

    ('2', 18, 0),
    ('1', 18, 0),
    ('3', 18, 1),
    ('4', 18, 0),

    ('int a1[][] = new int[][3];', 19, 1),
    ('int a2[][] = new int[2][3];', 19, 0),
    ('int a3[][] = new int[2][];', 19, 0),
    ('int a4[][] = {{}, {}, {}};', 19, 0),

    ('Mảng có thể lưu giữ các phần tử thuộc nhiều kiểu dữ liệu khác nhau', 20, 0),
    ('Chỉ số của mảng có thể sử dụng kiểu số thực (float, double)', 20, 0),
    ('Biểu thức array.length được sử dụng để trả về số phần tử trong mảng', 20, 1),
    ('Một phần tử của mảng không thể truyền vào trong một phương thức.', 20, 0),

    ('Một phương pháp quản lý dự án', 21, 1),
    ('Một ngôn ngữ lập trình', 21, 0),
    ('Một công cụ phân tích dữ liệu', 21, 0),
    ('Một quy trình kiểm thử phần mềm', 21, 0),

    ('Quản lý dự án và tài nguyên', 22, 0),
    ('Lập trình và phân tích kỹ thuật', 22, 0),
    ('Hỗ trợ và lãnh đạo trong việc áp dụng Scrum', 22, 1),
    ('Đảm bảo chất lượng sản phẩm', 22, 0),

    ('Scrum Master, Product Owner và Developers', 23, 1),
    ('Scrum Master, QA và Developers', 23, 0),
    ('Product Owner, QA và Developers', 23, 0),
    ('Scrum Master, Product Owner và Managers', 23, 0),

    ('Một tài liệu về quy trình làm việc', 24, 0),
    ('Một báo cáo tiến độ dự án', 24, 0),
    ('Một kỹ thuật lập trình', 24, 0),
    ('Một tài liệu mô tả yêu cầu từ người dùng', 24, 1),

    ('Daily Scrum và Sprint Review', 25, 0),
    ('Sprint Review và Sprint Planning', 25, 0),
    ('Sprint Planning và Retrospective', 25, 0),
    ('Daily Scrum và Sprint Planning', 25, 1),

    ('Một khung thời gian ngắn để phát triển sản phẩm', 26, 1),
    ('Một công cụ quản lý dự án', 26, 0),
    ('Một tài liệu yêu cầu dự án', 26, 0),
    ('Một quy trình kiểm thử phần mềm', 26, 0),

    ('Theo dõi tiến độ công việc trong Sprint', 27, 1),
    ('Quản lý nguồn lực và tài nguyên dự án', 27, 0),
    ('Xác định yêu cầu của khách hàng', 27, 0),
    ('Phân tích và thiết kế hệ thống', 27, 0),

    ('Đánh giá và xem xét kết quả của mỗi Sprint', 28, 1),
    ('Xác định và ưu tiên các công việc trong Sprint tiếp theo', 28, 0),
    ('Đánh giá tiến độ dự án và xác định rủi ro', 28, 0),
    ('Đưa ra các quyết định quan trọng về dự án', 28, 0),

    ('Xác định và quản lý nguồn lực dự án', 29, 0),
    ('Quyết định cách thức thực hiện công việc', 29, 1),
    ('Định nghĩa yêu cầu của dự án', 29, 0),

    ('Quản lý tiến độ dự án và tài nguyên', 30, 0),
    ('Xác định yêu cầu và ưu tiên công việc', 30, 1),
    ('Hỗ trợ và lãnh đạo trong việc áp dụng Scrum', 30, 0),
    ('Phân tích và thiết kế hệ thống', 30, 0),

    ('a) new', 31, 1),
    ('b) create', 31, 0),
    ('c) instantiate', 31, 0),
    ('d) object', 31, 0),

    ('a) extends', 32, 1),
    ('b) implements', 32, 0),
    ('c) inherits', 32, 0),
    ('d) derives', 32, 0),

    ('a) Một lớp cha được kế thừa bởi các lớp con.', 33, 0),
    ('b) Một kiểu dữ liệu đặc biệt mô tả một tập hợp các phương thức mà một đối tượng có thể triển khai.', 33, 1),
    ('c) Một cấu trúc dữ liệu để lưu trữ dữ liệu.', 33, 0),
    ('d) Một loại biến để lưu trữ giá trị.', 33, 0),

    ('a) stop', 34, 0),
    ('b) prevent', 34, 0),
    ('c) final', 34, 1),
    ('d) sealed', 34, 0),

    ('a) start()', 35, 0),
    ('b) run()', 35, 0),
    ('c) initialize()', 35, 0),
    ('d) constructor()', 35,1),

    ('a) this', 36, 0),
    ('b) super', 36, 1),
    ('c) that', 36, 0),
    ('d) the', 36, 0),

    ('a) Đúng', 37, 1),
    ('b) Sai', 37, 0),

    ('a) Khả năng một lớp có thể kế thừa từ nhiều lớp cha.', 38, 1),
    ('b) Khả năng một phương thức có thể có nhiều tham số.', 38, 0),
    ('c) Khả năng một phương thức có thể có nhiều định nghĩa khác nhau trong các lớp con.', 38, 0),
    ('d) Khả năng một lớp có thể triển khai nhiều interface.', 38, 0),

    ('a) this', 39, 0),
    ('b) super', 39, 1),
    ('c) base', 39, 0),
    ('d) parent', 39, 0),

    ('a) Đúng', 40, 1),
    ('b) Sai', 40, 0),

    ('a) Ghi đè là khi một phương thức có cùng tên, cùng kiểu trả về và cùng tham số được triển khai trong lớp con, trong khi nạp chồng là khi một phương thức có cùng tên nhưng khác kiểu trả về hoặc tham số.', 41, 1),
    ('b) Ghi đè là khi một phương thức có cùng tên, cùng kiểu trả về và cùng tham số được triển khai trong lớp cha, trong khi nạp chồng là khi một phương thức có cùng tên nhưng khác kiểu trả về hoặc tham số.', 41, 0),
    ('c) Ghi đè là khi một phương thức có cùng tên, cùng kiểu trả về và cùng tham số được triển khai trong lớp cha, trong khi nạp chồng là khi một phương thức có cùng tên, cùng kiểu trả về và cùng tham số được triển khai trong lớp con.', 41, 0),
    ('d) Ghi đè là khi một phương thức có cùng tên nhưng khác kiểu trả về hoặc tham số, trong khi nạp chồng là khi một phương thức có cùng tên, cùng kiểu trả về và cùng tham số được triển khai trong lớp con.', 41, 0),

    ('a) abstract', 42, 0),
    ('b) final', 42, 0),
    ('c) static', 42, 0),
    ('d) override', 42, 1),

    ('a) Đúng', 43, 1),
    ('b) Sai', 43, 0);

