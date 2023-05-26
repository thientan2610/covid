/*CREATE DATABASE qlCovid ON PRIMARY
( NAME = qlCovid_data, FILENAME = 'D:\qlCovid_data.mdf', SIZE = 10, MAXSIZE = 1000, FILEGROWTH = 10)
LOG ON
( NAME = qlCovid_log, FILENAME = 'D:\qlCovid_log.ldf', SIZE = 10, FILEGROWTH = 5)*/

USE qlCovid
GO
/*DROP TABLE TREATMENTRECORD
DROP TABLE LOGINRECORD
DROP TABLE PAYMENTRECORD
DROP TABLE RECEIPT_DETAIL
DROP TABLE RECEIPT
DROP TABLE PACK_DETAIL
DROP TABLE PACK
DROP TABLE PRODUCT
DROP TABLE RELATED
DROP TABLE USERS
DROP TABLE TREATMENTLOCATION
DROP TABLE LOCATION
DROP TABLE WARD
DROP TABLE DISTRICT
DROP TABLE CITY
DROP TABLE ACCOUNT
DROP FUNCTION AUTO_IDTR
DROP FUNCTION AUTO_IDLR
DROP FUNCTION AUTO_IDPR
DROP PROC proc_GetAddress
DROP PROC proc_UpdAddress
DROP VIEW v_ConsumePack
DROP PROC usp_Total*/
CREATE FUNCTION AUTO_IDTR()
RETURNS VARCHAR(6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(TrmtRecordID) FROM TREATMENTRECORD) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(TrmtRecordID, 3)) FROM TREATMENTRECORD
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'TR000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'TR00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 THEN 'TR0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
CREATE FUNCTION AUTO_IDLR()
RETURNS VARCHAR(6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(LogRecID) FROM LOGINRECORD) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(LogRecID, 3)) FROM LOGINRECORD
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'LR000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'LR00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 THEN 'LR0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
CREATE FUNCTION AUTO_IDPR()
RETURNS VARCHAR(6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(PayRecID) FROM PAYMENTRECORD) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(PayRecID, 3)) FROM PAYMENTRECORD
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'PR000' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'PR00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 99 THEN 'PR0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
---------------------------------------------------
--ACCOUNT
CREATE TABLE ACCOUNT (
	NoID varchar(12) NOT NULL PRIMARY KEY,
	Pass varchar(100) NOT NULL,
	Roles tinyint,
	CHECK(Roles = 1 OR Roles = 2 OR Roles = 3) 
)

---------------------------------------------------
--USERS
CREATE TABLE USERS (
	UserID char(6) NOT NULL PRIMARY KEY,
	Username varchar(50) NOT NULL,
	NoID varchar(12) NOT NULL,
	BirthYear smallint NOT NULL,
	AddressID char(6),
	TrmtLocaID char(6),
	UserStatus smallint NOT NULL,
	DebitBalance int	
)
---------------------------------------------------
--LOGINRECORD
CREATE TABLE LOGINRECORD(
	LogRecID char(6) PRIMARY KEY CONSTRAINT IDLR DEFAULT DBO.AUTO_IDLR(),
	NoID varchar(12),
	LogTimestamp datetime
)
---------------------------------------------------
--LOCATION
CREATE TABLE LOCATION(
	AddressID char(6) NOT NULL PRIMARY KEY,
	AddressName varchar(50) NOT NULL,
	WardID char(4) NOT NULL
)

---------------------------------------------------
--WARD
CREATE TABLE WARD(
	WardID char(4) NOT NULL PRIMARY KEY,
	WardName nvarchar(30) NOT NULL,
	DistrictID char(4) NOT NULL
)

---------------------------------------------------
--DISTRICT
CREATE TABLE DISTRICT (
	DistrictID char(4) NOT NULL PRIMARY KEY,
	DistrictName varchar(30) NOT NULL,
	CityID char(4) NOT NULL
)

---------------------------------------------------
--CITY
CREATE TABLE CITY (
	CityID char(4) NOT NULL PRIMARY KEY,
	CityName varchar(30) NOT NULL
)

---------------------------------------------------
--TREATMENTLOCATION
CREATE TABLE TREATMENTLOCATION (
	TrmtLocaID char(6) NOT NULL PRIMARY KEY,
	TrmtLocaName varchar(50) NOT NULL,
	Capacity int  NOT NULL,
	Occupancy int NOT NULL,
	CHECK (Capacity > 0), 
	CHECK (Occupancy <= Capacity) 
)

---------------------------------------------------
--TREATMENTRECORD
CREATE TABLE TREATMENTRECORD (
	TrmtRecordID char(6) PRIMARY KEY CONSTRAINT IDKH DEFAULT DBO.AUTO_IDTR(),
	UserID char(6) NOT NULL,
	TrmtLocaID char(6),
	UserStatus tinyint NOT NULL,
	RecordTimestamp datetime,
)

---------------------------------------------------
--PRODUCT
CREATE TABLE PRODUCT (
	ProductID char(6) NOT NULL PRIMARY KEY,
	ProductName varchar(100) NOT NULL,
	Unit varchar(15),
	Price int,
	ProductImage image
)

---------------------------------------------------
--PACK
CREATE TABLE PACK (
	PackID char(6) NOT NULL PRIMARY KEY,
	PackName varchar(50) NOT NULL,
	Price int,
	LimitedQuantity tinyint,
	Deadline date
)

---------------------------------------------------
--PACK_DETAIL
CREATE TABLE PACK_DETAIL (
	PackID char(6) NOT NULL,
	ProductID char(6) NOT NULL,
	Quantity smallint NOT NULL,
	PRIMARY KEY (PackID, ProductID)
)

---------------------------------------------------
--RECEIPT
CREATE TABLE RECEIPT (
	ReceiptID char(6) NOT NULL PRIMARY KEY,
	UserID char(6) NOT NULL,
	OrderDate date NOT NULL,
	ReceiptStatus bit NOT NULL,
	RemainAmount int NOT NULL
)

---------------------------------------------------
--RECEIPT_DETAIL
CREATE TABLE RECEIPT_DETAIL(
	ReceiptID char(6) NOT NULL,
	PackID char(6) NOT NULL,
	Quantity smallint NOT NULL,
	PRIMARY KEY (ReceiptID, PackID)
)

---------------------------------------------------
--PAYMENTRECORD
CREATE TABLE PAYMENTRECORD (
	PayRecID char(6) PRIMARY KEY CONSTRAINT IDPR DEFAULT DBO.AUTO_IDPR(),
	UserID char(6)  NOT NULL,
	AmountOfMoney int NOT NULL,
	PayTimestamp datetime
)
---------------------------------------------------
--RELATED
CREATE TABLE RELATED (
	UserID_1 char(6) NOT NULL,
	UserID_2 char(6) NOT NULL,
	Reason varchar(50),
	PRIMARY KEY(UserID_1, UserID_2)
)

---------------------------------------------------
-- Khoa ngoai cho bang USERS
ALTER TABLE USERS ADD CONSTRAINT FK03_USERS FOREIGN KEY(NoID) REFERENCES ACCOUNT(NoID)
ALTER TABLE USERS ADD CONSTRAINT FK01_USERS FOREIGN KEY(AddressID) REFERENCES LOCATION(AddressID)
ALTER TABLE USERS ADD CONSTRAINT FK02_USERS FOREIGN KEY(TrmtLocaID) REFERENCES TREATMENTLOCATION(TrmtLocaID)

-- Khoa ngoai cho bang LOGINRECORD
ALTER TABLE LOGINRECORD ADD CONSTRAINT FK_LOGINRECORD FOREIGN KEY(NoID) REFERENCES ACCOUNT(NoID)
ALTER TABLE LOGINRECORD ADD CONSTRAINT DF_LogTimestamp DEFAULT GETDATE() FOR LogTimestamp

-- Khoa ngoai cho bang PAYMENTRECORD
ALTER TABLE PAYMENTRECORD ADD CONSTRAINT FK_PAYMENTRECORD FOREIGN KEY(UserID) REFERENCES USERS(UserID)
ALTER TABLE PAYMENTRECORD ADD CONSTRAINT DF_PayTimestamp DEFAULT GETDATE() FOR PayTimestamp

-- Khoa ngoai cho bang LOCATION
ALTER TABLE LOCATION ADD CONSTRAINT FK01_LOCATION FOREIGN KEY(WardID) REFERENCES WARD(WardID)

-- Khoa ngoai cho bang WARD
ALTER TABLE WARD ADD CONSTRAINT FK_WARD FOREIGN KEY(DistrictID) REFERENCES DISTRICT(DistrictID)

-- Khoa ngoai cho bang DISTRICT
ALTER TABLE DISTRICT ADD CONSTRAINT FK_DISTRICT FOREIGN KEY(CityID) REFERENCES CITY(CityID)

-- Khoa ngoai cho bang TREATMENTRECORD
ALTER TABLE TREATMENTRECORD ADD CONSTRAINT FK01_TREATMENTRECORD FOREIGN KEY(UserID) REFERENCES USERS(UserID)
ALTER TABLE TREATMENTRECORD ADD CONSTRAINT FK02_TREATMENTRECORD FOREIGN KEY(TrmtLocaID) REFERENCES TREATMENTLOCATION(TrmtLocaID)

-- Khoa ngoai cho bang PACK_DETAIL
ALTER TABLE PACK_DETAIL ADD CONSTRAINT FK01_PACK_DETAIL FOREIGN KEY(PackID) REFERENCES PACK(PackID)
ALTER TABLE PACK_DETAIL ADD CONSTRAINT FK02_PACK_DETAIL FOREIGN KEY(ProductID) REFERENCES PRODUCT(ProductID)

-- Khoa ngoai cho bang RECEIPT
ALTER TABLE RECEIPT ADD CONSTRAINT FK_RECEIPT FOREIGN KEY(UserID) REFERENCES USERS(UserID)

-- Khoa ngoai cho bang RECEIPT_DETAIL
ALTER TABLE RECEIPT_DETAIL ADD CONSTRAINT FK01_RECEIPT_DETAIL FOREIGN KEY(ReceiptID) REFERENCES RECEIPT(ReceiptID)
ALTER TABLE RECEIPT_DETAIL ADD CONSTRAINT FK02_RECEIPT_DETAIL FOREIGN KEY(PackID) REFERENCES PACK(PackID)

-- Khoa ngoai cho bang RELATED
ALTER TABLE RELATED ADD CONSTRAINT FK01_RELATED FOREIGN KEY(UserID_1) REFERENCES USERS(UserID)
ALTER TABLE RELATED ADD CONSTRAINT FK02_RELATED FOREIGN KEY(UserID_2) REFERENCES USERS(UserID)
GO
---------------------------------------------------
--TRIGGER 
-- Add automatically to TREATMENTRECORD when ins or upd dbo.USERS
CREATE TRIGGER tr_TREATMENTRECORD
ON USERS
AFTER INSERT, UPDATE
AS 
	BEGIN
	SET NOCOUNT ON;
	INSERT INTO TREATMENTRECORD(
		UserID,
		TrmtLocaID,
		UserStatus,
		RecordTimestamp
	)
	SELECT ins.UserID, ins.TrmtLocaID, ins.UserStatus, GETDATE()
	FROM inserted ins
	END
GO
---------------------------------------------------
---------------------------------------------------
set dateformat dmy
---------------------------------------------------

--CITY
insert into CITY values('TP01', 'Ho Chi Minh City')
insert into CITY values('TP02', 'Ha Noi ')
insert into CITY values('TP03', 'Khanh Hoa Province')
insert into CITY values('TP04', 'Đa Nang')
insert into CITY values('TP05', 'Hai Phong')

--DISTRICT
insert into DISTRICT values('Q001', 'District 1', 'TP01')
insert into DISTRICT values('Q002', 'District 3', 'TP01')
insert into DISTRICT values('Q003', 'District 5', 'TP01')
insert into DISTRICT values('Q004', 'District 7', 'TP01')
insert into DISTRICT values('Q005', 'District 10', 'TP01')
insert into DISTRICT values('Q006', 'Ba Đinh District', 'TP02')
insert into DISTRICT values('Q007', 'Cau Giay District', 'TP02')
insert into DISTRICT values('Q008', 'Dong Da District', 'TP02')
insert into DISTRICT values('Q009', 'Hoan Kiem District', 'TP02')
insert into DISTRICT values('Q010', 'Hoang Mai District', 'TP02')
insert into DISTRICT values('Q011', 'Nha Trang City', 'TP03')
insert into DISTRICT values('Q012', 'Cam Ranh City', 'TP03')
insert into DISTRICT values('Q013', 'Cam Lam District', 'TP03')
insert into DISTRICT values('Q014', 'Van Ninh District', 'TP03')
insert into DISTRICT values('Q015', 'Dien Khanh District', 'TP03')
insert into DISTRICT values('Q016', 'Ngu Hanh Son District', 'TP04')
insert into DISTRICT values('Q017', 'Thanh Khe District', 'TP04')
insert into DISTRICT values('Q018', 'Cam Le District', 'TP04')
insert into DISTRICT values('Q019', 'Son Tra District', 'TP04')
insert into DISTRICT values('Q020', 'Hai Chau District', 'TP04')
insert into DISTRICT values('Q021', 'Đo Son District', 'TP05')
insert into DISTRICT values('Q022', 'Ngo Quyen District', 'TP05')
insert into DISTRICT values('Q023', 'Hong Bang District', 'TP05')
insert into DISTRICT values('Q024', 'Kien An District', 'TP05')
insert into DISTRICT values('Q025', 'Hai An District', 'TP05')

--WARD
insert into WARD values('P001', 'Đa Kao Ward', 'Q001')
insert into WARD values('P002', 'Ben Nghe Ward', 'Q001')
insert into WARD values('P003', 'Ben Thanh Ward', 'Q001')
insert into WARD values('P004', 'Co Giang Ward', 'Q001')
insert into WARD values('P005', 'Cau Ong Lanh Ward', 'Q001')
insert into WARD values('P006', 'Ward 01', 'Q002')
insert into WARD values('P007', 'Ward 04', 'Q002')
insert into WARD values('P008', 'Ward 09', 'Q002')
insert into WARD values('P009', 'Ward 10', 'Q002')
insert into WARD values('P010', 'Ward 12', 'Q002')
insert into WARD values('P011', 'Ward 01', 'Q003')
insert into WARD values('P012', 'Ward 06', 'Q003')
insert into WARD values('P013', 'Ward 10', 'Q003')
insert into WARD values('P014', 'Ward 13', 'Q003')
insert into WARD values('P015', 'Ward 14', 'Q003')
insert into WARD values('P016', 'Tan Kieng Ward', 'Q004')
insert into WARD values('P017', 'Tan Quy Ward', 'Q004')
insert into WARD values('P018', 'Tan Phu Ward', 'Q004')
insert into WARD values('P019', 'Tan Thuan Đong Ward', 'Q004')
insert into WARD values('P020', 'Tan Thuan Tay Ward', 'Q004')
insert into WARD values('P021', 'Ward 10', 'Q005')
insert into WARD values('P022', 'Ward 12', 'Q005')
insert into WARD values('P023', 'Ward 13', 'Q005')
insert into WARD values('P024', 'Phường 14', 'Q005')
insert into WARD values('P025', 'Ward 15', 'Q005')
insert into WARD values('P026', 'Truc Bach Ward', 'Q006')
insert into WARD values('P027', 'Lieu Giai Ward', 'Q006')
insert into WARD values('P028', 'Cong Vi Ward', 'Q006')
insert into WARD values('P029', 'Đien Bien Ward', 'Q006')
insert into WARD values('P030', 'Quan Thanh Ward', 'Q006')
insert into WARD values('P031', 'Nghia Đo Ward', 'Q007')
insert into WARD values('P032', 'Nghia Tan Ward', 'Q007')
insert into WARD values('P033', 'Dich Vong Ward', 'Q007')
insert into WARD values('P034', 'Dich Vong Hau Ward', 'Q007')
insert into WARD values('P035', 'Quan Hoa Ward', 'Q007')
insert into WARD values('P036', 'Van Mieu Ward', 'Q008')
insert into WARD values('P037', 'Quoc Tu Giam Ward', 'Q008')
insert into WARD values('P038', 'Kham Thien Ward', 'Q008')
insert into WARD values('P039', 'Kim Lien Ward', 'Q008')
insert into WARD values('P040', 'Quang Trung Ward', 'Q008')
insert into WARD values('P041', 'Hang Trong Ward', 'Q009')
insert into WARD values('P042', 'Hang Bong Ward', 'Q009')
insert into WARD values('P043', 'Cua Nam Ward', 'Q009')
insert into WARD values('P044', 'Trang Tien Ward', 'Q009')
insert into WARD values('P045', 'Hang Bai Ward', 'Q009')
insert into WARD values('P046', 'Tuong Mai Ward', 'Q010')
insert into WARD values('P047', 'Mai Đong Ward', 'Q010')
insert into WARD values('P048', 'Tan Mai Ward', 'Q010')
insert into WARD values('P049', 'Giap Bat Ward', 'Q010')
insert into WARD values('P050', 'Linh Nam Ward', 'Q010')
insert into WARD values('P051', 'Loc Tho Ward', 'Q011')
insert into WARD values('P052', 'Vinh Tho Ward', 'Q011')
insert into WARD values('P053', 'Van Thanh Ward', 'Q011')
insert into WARD values('P054', 'Tan Lap Ward', 'Q011')
insert into WARD values('P055', 'Vinh Hoa Ward', 'Q011')
insert into WARD values('P056', 'Ba Ngoi Ward', 'Q012')
insert into WARD values('P057', 'Cam Thuan Ward', 'Q012')
insert into WARD values('P058', 'Cam Loi Ward', 'Q012')
insert into WARD values('P059', 'Cam Loc Ward', 'Q012')
insert into WARD values('P060', 'Cam Linh Ward', 'Q012')
insert into WARD values('P061', 'Cam Tan Commune', 'Q013')
insert into WARD values('P062', 'Cam Hoa Commune', 'Q013')
insert into WARD values('P063', 'Cam Hai Đong Commune', 'Q013')
insert into WARD values('P064', 'Cam Hai Tay Commune', 'Q013')
insert into WARD values('P065', 'Cam Hiep Bac Commune', 'Q013')
insert into WARD values('P066', 'Đai Lanh Commune', 'Q014')
insert into WARD values('P067', 'Van Tho Commune', 'Q014')
insert into WARD values('P068', 'Van Long Commune', 'Q014')
insert into WARD values('P069', 'Van Binh Commune', 'Q014')
insert into WARD values('P070', 'Van Phuoc Commune', 'Q014')
insert into WARD values('P071', 'Dien Phuoc Commune', 'Q015')
insert into WARD values('P072', 'Dien Son Commune', 'Q015')
insert into WARD values('P073', 'Dien Lac Commune', 'Q015')
insert into WARD values('P074', 'Dien Xuan Commune', 'Q015')
insert into WARD values('P075', 'Dien Phu Commune', 'Q015')
insert into WARD values('P076', 'My An Ward', 'Q016')
insert into WARD values('P077', 'Khue My Ward', 'Q016')
insert into WARD values('P078', 'Hoa Quy Ward', 'Q016')
insert into WARD values('P079', 'Hoa Hai Ward', 'Q016')
insert into WARD values('P080', 'Khue An Ward', 'Q016')
insert into WARD values('P081', 'Tam Thuan Ward', 'Q017')
insert into WARD values('P082', 'Thanh Khe Tay Ward', 'Q017')
insert into WARD values('P083', 'Thanh Khe Đong Ward', 'Q017')
insert into WARD values('P084', 'Vinh Trung Ward', 'Q017')
insert into WARD values('P085', 'An Khe Ward', 'Q017')
insert into WARD values('P086', 'Hoa Phat Ward', 'Q018')
insert into WARD values('P087', 'Hoa An Ward', 'Q018')
insert into WARD values('P088', 'Hoa Tho Tay Ward', 'Q018')
insert into WARD values('P089', 'Hoa Tho Đong Ward', 'Q018')
insert into WARD values('P090', 'Hoa Xuan Ward', 'Q018')
insert into WARD values('P091', 'An Hai Bac Ward', 'Q019')
insert into WARD values('P092', 'An Hai Tay Ward', 'Q019')
insert into WARD values('P093', 'An Hai Đong Ward', 'Q019')
insert into WARD values('P094', 'Man Thai Ward', 'Q019')
insert into WARD values('P095', 'Phuoc My Ward', 'Q019')
insert into WARD values('P096', 'Hai Chau I Ward', 'Q020')
insert into WARD values('P097', 'Hai Chau II Ward', 'Q020')
insert into WARD values('P098', 'Hoa Thuan Tay Ward', 'Q020')
insert into WARD values('P099', 'Hoa Thuan Đong Ward', 'Q020')
insert into WARD values('P100', 'Binh Hien Ward', 'Q020')
insert into WARD values('P101', 'Ngoc Xuyen Ward', 'Q021')
insert into WARD values('P102', 'Hai Son Ward', 'Q021')
insert into WARD values('P103', 'Bang La Ward', 'Q021')
insert into WARD values('P104', 'Van Huong Ward', 'Q021')
insert into WARD values('P105', 'Minh Đuc Ward', 'Q021')
insert into WARD values('P106', 'Cau Tre Ward', 'Q022')
insert into WARD values('P107', 'Van My Ward', 'Q022')
insert into WARD values('P108', 'May To Ward', 'Q022')
insert into WARD values('P109', 'Lac Vien Ward', 'Q022')
insert into WARD values('P110', 'Gia Vien Ward', 'Q022')
insert into WARD values('P111', 'Hung Vuong Ward', 'Q023')
insert into WARD values('P112', 'Thuong Ly Ward', 'Q023')
insert into WARD values('P113', 'Ha Ly Ward', 'Q023')	
insert into WARD values('P114', 'Minh Khai Ward', 'Q023')
insert into WARD values('P115', 'Trai Chuoi Ward', 'Q023')
insert into WARD values('P116', 'Đong Hoa Ward', 'Q024')
insert into WARD values('P117', 'Bac Son Ward', 'Q024')
insert into WARD values('P118', 'Nam Son Ward', 'Q024')
insert into WARD values('P119', 'Ngoc Son Ward', 'Q024')
insert into WARD values('P120', 'Phu Lien Ward', 'Q024')
insert into WARD values('P121', 'Đong Hai 1 Ward', 'Q025')
insert into WARD values('P122', 'Đong Hai 2 Ward', 'Q025')
insert into WARD values('P123', 'Đang Hai Ward', 'Q025')
insert into WARD values('P124', 'Đang Lam Ward', 'Q025')
insert into WARD values('P125', 'Nam Hai Ward', 'Q025')

--LOCATION
insert into LOCATION values('DC0001', 'Lane 180, Nguyen Luong Bang Street', 'P040')
insert into LOCATION values('DC0002', '78, Giai Phong Street', 'P037')
insert into LOCATION values('DC0003', '764, Vo Van Kiet Street', 'P011')
insert into LOCATION values('DC0004', '266, Ly Thuong Kiet Street', 'P024')
insert into LOCATION values('DC0005', '122, Hai Phong Street', 'P098')
insert into LOCATION values('DC0006', '124 P. Nguyen Đuc Canh Street', 'P114')
insert into LOCATION values('DC0007', '332 Nguyen Thai Hoc Street', 'P100')
insert into LOCATION values('DC0008', '27 Le Van Quoi Street', 'P023')
insert into LOCATION values('DC0009', '245 Nguyen Cong Tru Street', 'P123')
insert into LOCATION values('DC0010', '54 Pasteur Street', 'P073')
insert into LOCATION values('DC0011', '343 Dong Khoi Street', 'P088')
insert into LOCATION values('DC0012', '981 Pham Van Hai Street', 'P090')
insert into LOCATION values('DC0013', '156 Le Thi Rieng Street', 'P040')
insert into LOCATION values('DC0014', '76 Vo Thi Sau Street', 'P066')
insert into LOCATION values('DC0015', '930 Tran Dai Nghia Street', 'P034')
insert into LOCATION values('DC0016', '342 Le Hong Phong Street', 'P077')
insert into LOCATION values('DC0017', '67 Nguyen Thi Minh Khai Street', 'P003')
insert into LOCATION values('DC0018', '654 Hai Ba Trung Street', 'P100')
insert into LOCATION values('DC0019', '34A Truong Sa Street', 'P055')
insert into LOCATION values('DC0020', '764 Tran Quoc Toan Street', 'P111')
insert into LOCATION values('DC0021', '8 Hoang Du Khuong Street', 'P023')
insert into LOCATION values('DC0022', '17 Hoang Van Thu Street', 'P048')
insert into LOCATION values('DC0023', '73 Ban Co Street', 'P106')
--TREATMENTLOCATION
insert into TREATMENTLOCATION values('BV0001', 'Dong Da General Hospital', 100, 100)
insert into TREATMENTLOCATION values('BV0002', 'Bach Mai Hospital', 300, 250)
insert into TREATMENTLOCATION values('BV0003', 'Hospital for Tropical Diseases', 440, 440)
insert into TREATMENTLOCATION values('BV0004', 'Trung Vuong Hospital for Covid-19', 1100, 900)
insert into TREATMENTLOCATION values('BV0005', 'C Da Nang Hospital', 200, 10)
insert into TREATMENTLOCATION values('BV0006', 'Hai Phong International Hospital', 100, 10)

--PRODUCT
insert into PRODUCT values('SP0001', 'Aba Laundry Detegent 1.5kg', N'Pack', 51000, NULL)
insert into PRODUCT values('SP0002', 'Hand sanitizer On1', 'Bottle', 65000, NULL)
insert into PRODUCT values('SP0003', 'ST25 Rice 2kg', 'Pack', 100000, NULL)
insert into PRODUCT values('SP0004', 'TH true MILK UHT Pure Fresh Milk (180ml)', '4-pack block', 34500, NULL)
insert into PRODUCT values('SP0005', 'Walnut Milk TH true NUT 180 ml', '4-pack block', 49000, NULL)
insert into PRODUCT values('SP0006', 'Macadamia nut milk TH true NUT 180 ml', '4-pack block', 49000, NULL)
insert into PRODUCT values('SP0007', 'Natural Ooolong – TH true TEA', 'Bottle', 7900, NULL)
insert into PRODUCT values('SP0008', 'NATURAL JUICE – ORANGE (99.94% ORANGE JUICE) – TH TRUE JUICE', 'Bottle', 17700, NULL)
insert into PRODUCT values('SP0009', 'TH true ICE CREAM Pure Chocolate Ice Cream', 'Pack', 15800, NULL)
insert into PRODUCT values('SP0010', 'Pure Chocolate – Natural Vanilla Ice Cream Sandwich', 'Pack', 21600, NULL)
insert into PRODUCT values('SP0011', 'Ong Tho sweetened condensed milk 380g', 'Can',19000, NULL)
insert into PRODUCT values('SP0012', 'Southern Star Sweetened Condensed Creamer 380g', 'Can',17000, NULL)
insert into PRODUCT values('SP0013', 'Vfresh 100% Fruit Juice-Apple 1L', 'Pack', 40000, NULL)
insert into PRODUCT values('SP0014', 'Natural Aloe Vera Yogurt', '4-pack block', 29500, NULL)
insert into PRODUCT values('SP0015', 'Natural Fruits Probiotic Yogurt', '4-pack block', 29500, NULL)
insert into PRODUCT values('SP0016', 'Pasteurized Drinking Yogurt – Strawberry – TH true', '4 bottle/pack', 35400, NULL)
insert into PRODUCT values('SP0017', 'Hao Hao Sour and Spicy Shrimp Flavored Noodle 75g', 'Pack', 3800, NULL)
insert into PRODUCT values('SP0018', 'Handy Hao Hao Sour and Spicy Shrimp Flavored Noodle 67g', 'Cup', 8600, NULL)
insert into PRODUCT values('SP0019', 'Hao Hao Vegetable and Mushroom Flavored Vagan Noodle 74g', 'Pack', 4000, NULL)
insert into PRODUCT values('SP0020', 'Hao Hao Stir-fried Noodles with green onion and shrimp flavor 75g', 'Pack', 4000, NULL)
insert into PRODUCT values('SP0021', 'OMACHI Golden Potato Noodles - Beef Stew Flavor 68g', 'Cup', 10500, NULL)
insert into PRODUCT values('SP0022', 'OMACHI Golden Potato Noodles - Braised Pork Rib 80g', 'Pack', 8000, NULL)
insert into PRODUCT values('SP0023', 'OMACHI Golden Potato Noodles - Hot & Sour Shrimp 80g', 'Pack', 8000, NULL)

--PACK
insert into PACK values('GH0001', 'Pack No.1', NULL, 8, NULL)
insert into PACK values('GH0002', 'Pack No.2', NULL, 1, NULL)
insert into PACK values('GH0003', 'Pack No.3', NULL, 3, NULL)
insert into PACK values('GH0004', 'Pack for Vegetarian', NULL, 4, NULL)
insert into PACK values('GH0005', 'Pack for Children', NULL, 3, NULL)


--PACK_DETAIL
insert into PACK_DETAIL values('GH0001', 'SP0009', 5)
insert into PACK_DETAIL values('GH0001', 'SP0010', 3)
insert into PACK_DETAIL values('GH0001', 'SP0008', 1)
insert into PACK_DETAIL values('GH0002', 'SP0001', 1)
insert into PACK_DETAIL values('GH0002', 'SP0002', 1)
insert into PACK_DETAIL values('GH0002', 'SP0011', 5)
insert into PACK_DETAIL values('GH0002', 'SP0003', 2)
insert into PACK_DETAIL values('GH0003', 'SP0003', 1)
insert into PACK_DETAIL values('GH0003', 'SP0013', 5)
insert into PACK_DETAIL values('GH0003', 'SP0012', 3)
insert into PACK_DETAIL values('GH0003', 'SP0004', 2)
insert into PACK_DETAIL values('GH0004', 'SP0004', 2)
insert into PACK_DETAIL values('GH0004', 'SP0005', 2)
insert into PACK_DETAIL values('GH0004', 'SP0006', 1)
insert into PACK_DETAIL values('GH0004', 'SP0007', 1)
insert into PACK_DETAIL values('GH0004', 'SP0010', 3)
insert into PACK_DETAIL values('GH0004', 'SP0013', 3)
insert into PACK_DETAIL values('GH0005', 'SP0009', 3)
insert into PACK_DETAIL values('GH0005', 'SP0011', 3)
insert into PACK_DETAIL values('GH0005', 'SP0012', 3)
insert into PACK_DETAIL values('GH0005', 'SP0004', 2)
insert into PACK_DETAIL values('GH0005', 'SP0003', 1)

--ACCOUNT 
insert into ACCOUNT values('010285904821','$2a$13$/zH.m17SFmYHFcA2QAVhhe37VfQ/UkzHLpHTY8vFgn5a7GNXIV86i', 3)
insert into ACCOUNT values('010285901821','$2a$13$foGpB7fmHyRdc5550PiX..OFwParoYW81EKufRGnDYxZijKXUtguq', 3)
insert into ACCOUNT values('010285902821','$2a$13$EsevZpneJhMSdeX4430ZDuLA06Grb9E1CsTHKTkQmHvIZ3W6z1cf6', 3)
insert into ACCOUNT values('010285903821','$2a$13$KVcRPCN4XsW9SOOWEjb18epJqwP..dQoMeFpJTvEyAk68aQ0BrPjq', 3)
insert into ACCOUNT values('010285905821','$2a$13$oIxL3Ws0KNjX6smIyTeCWuOgOi9LC6Ejj0l6Psxrw/x4rZqFpejai', 3)
insert into ACCOUNT values('010285906821','$2a$13$WWWmTv4lTeZfyQqwDsSy/uxJezAl4hMtaxJ3r5A8qF9O5yrdJKdHq', 3)
insert into ACCOUNT values('010285907821','$2a$13$ExXJsq949YoeD4nhdFffMeuN/hDRfACAKNFcGDpl8H29u4QDdbwV.', 3)
insert into ACCOUNT values('010285908821','$2a$13$znUQB10l85GujIYuLPB82OQYaxCeaLQoa4IrFj7mNvsZYyUU/1Osu', 3)
insert into ACCOUNT values('010285909821','$2a$13$f4UfiuZN/VWGYqWgzePW1eVkSSEzJeWZm6n3JGCjwuvMnmpzPGQNW', 3)
insert into ACCOUNT values('010285901121','$2a$13$LQt4IrbWmrR8nPWpaAqvGOXq/SjZpWnkQnJnZqBdSXMHSupuMJmnu', 3)
insert into ACCOUNT values('010285900121','$2a$13$bGX5iHa5fh9HRZkC6iC2auI7WTUTkxFI1NWD5pRlf4WqEwEkdLox6', 3)
insert into ACCOUNT values('010285902121','$2a$13$RLwsGJiistoNw95hDuas6uPUml5HfY8pRYH62PUdi9vBIZz6d8A3S', 3)
insert into ACCOUNT values('010285903121','$2a$13$jyfDSaZCfIrGA.JYWZOLW.kEsaUQX0mroCgCyMrpVyk6ZfQuN/lcW', 3)
insert into ACCOUNT values('010285904121','$2a$13$EJf0cZU9NTsn//Kwra7g.ubzhGASj9sPR1tsUYLhRvgh1Kct7Et3G', 3)
insert into ACCOUNT values('010285905121','$2a$13$kgRtStkZAq4dgY3CvLGDX.23r2MF1Qn0XuqIicQ6hE9Iv9lxSmVKa', 3)
insert into ACCOUNT values('010285906121','$2a$13$PaYFGDKE9B5pcLdRTZNw.u0M5R0lC9y9jO9KgRu/EDa2/N8HQBB2C', 3)
insert into ACCOUNT values('010285907121','$2a$13$4vHrp1TCdqNf8av5NshNleQrhDzYezkuL60kB42yx/oTjyUO207Wu', 3)
insert into ACCOUNT values('010285908121','$2a$13$imLx45fd4JqzrtcqpqgwXO4aXMgYgFvHVnby1LaMB52uXDlGM.pEq', 2)
insert into ACCOUNT values('010285909121','$2a$13$6xsj8bnkxoEtrXD7gLw9GuMR25xHfeKDcu1W3N3qr2kC0RoxJK3Pe', 2)
insert into ACCOUNT values('010285901221','$2a$13$CVM4ydu5u6cztU.B5fdAce0F.0t22lzIXr9q.xazC51caZWdTml3G', 2)
insert into ACCOUNT values('010285900221','$2a$13$mfaCoG6tY6o5m5uArVqN1e8D305AmZVttFaIlChQEcEm1B/kY9v2C', 2)
insert into ACCOUNT values('010285902221','$2a$13$Dre/7qEymxe88ynTLIvMQuyoiVxiCMUK/gD0InsuAx9ITH8KObYzq', 2)
insert into ACCOUNT values('010285903221','$2a$13$Q7trPgyOjgLsccTcb8ezOei/dWvQ0R1UX/vdVySVPAg3/bYhn59PK', 1)

--USERS
insert into USERS values('BN0001', 'Jackson Wang', '010285904821', 1994, 'DC0007', 'BV0001', 0, 50000)
insert into USERS values('BN0002', 'Evan Lin', '010285901821', 1995, 'DC0008', NULL, 1, 200000)
insert into USERS values('BN0003', 'Cai Xukun', '010285902821', 1998, 'DC0009', NULL, 2, 200000)
insert into USERS values('BN0004', 'Lil Ghost', '010285903821', 1999, 'DC0010', 'BV0001', 0, 200000)
insert into USERS values('BN0005', 'Chen Linong', '010285905821', 2000, 'DC0011', 'BV0004', 0, 200000)
insert into USERS values('BN0006', 'Adam Fan', '010285906821', 2000, 'DC0012', 'BV0002', 0, 200000)
insert into USERS values('BN0007', 'Boogie', '010285907821', 1996, 'DC0013', NULL, 1, 200000)
insert into USERS values('BN0008', 'Azorachin', '010285908821', 1994, 'DC0014', NULL, 2, 200000)
insert into USERS values('BN0009', 'Justin Huang', '010285909821', 2002, 'DC0015', 'BV0002', 0, 200000)
insert into USERS values('BN0010', 'Theo', '010285901121', 1996, 'DC0016', NULL, 1, 200000)
insert into USERS values('BN0011', 'Xevier', '010285900121', 1993, 'DC0017', NULL, 2, 200000)
insert into USERS values('BN0012', 'Chikada Rikimaru', '010285902121', 1993, 'DC0018', 'BV0005', 0, 200000)
insert into USERS values('BN0013', 'Uno Santa', '010285903121', 1998, 'DC0019', 'BV0005', 0, 200000)
insert into USERS values('BN0014', 'Daniel Zhou', '010285904121', 2002, 'DC0020', 'BV0003', 0, 200000)
insert into USERS values('BN0015', 'Akira Liu', '010285905121', 1999, 'DC0021', 'BV0004', 0, 200000)
insert into USERS values('BN0016', 'Yang Yang', '010285906121', 1991, 'DC0022', NULL, 2, 200000)
insert into USERS values('BN0017', 'Kim Chungha', '010285907121', 1996, 'DC0023', NULL, 1, 200000)

--RELATED
insert into RELATED values('BN0001', 'BN0015', NULL)
insert into RELATED values('BN0001', 'BN0002', NULL)
insert into RELATED values('BN0001', 'BN0005', NULL)
insert into RELATED values('BN0001', 'BN0017', NULL)
insert into RELATED values('BN0017', 'BN0016', NULL)
insert into RELATED values('BN0017', 'BN0011', NULL)
insert into RELATED values('BN0017', 'BN0008', NULL)
insert into RELATED values('BN0005', 'BN0004', NULL)
insert into RELATED values('BN0005', 'BN0006', NULL)
insert into RELATED values('BN0014', 'BN0013', NULL)
insert into RELATED values('BN0014', 'BN0010', NULL)
insert into RELATED values('BN0014', 'BN0007', NULL)

GO
---------------------------------------------------
--VIEW
--
CREATE VIEW v_ConsumePack
AS
	SELECT PK.PackID, PK.PackName, COUNT(RD.PackID) AS Quantity 
	FROM RECEIPT_DETAIL RD INNER JOIN PACK PK ON RD.PackID = PK.PackID
	GROUP BY PK.PackID, PK.PackName
GO
---------------------------------------------------
--STORED PROCEDURE + TRANSACTION
CREATE PROC proc_GetAddress
	@addid char(6)
AS
BEGIN
	SELECT L.AddressName, W.WardName, D.DistrictName, C.CityName FROM ((LOCATION L INNER JOIN WARD W ON L.WardID = W.WardID) INNER JOIN DISTRICT D ON W.DistrictID = D.DistrictID) INNER JOIN CITY C ON C.CityID = D.CityID WHERE L.AddressID = @addid
END

GO
CREATE PROC proc_UpdAddress
	@addid char(6), @addname varchar(50), @wid char(4)
AS
BEGIN
	UPDATE LOCATION SET AddressName = @addname, WardID = @wid WHERE AddressID = @addid
	RETURN 1;
END
GO
CREATE PROC usp_Total
	@madh char(6)
AS
BEGIN
	select SUM(DD.Quantity * P.Price) as tongtien
	FROM RECEIPT_DETAIL DD JOIN RECEIPT DN ON DD.ReceiptID = DN.ReceiptID LEFT JOIN PACK P ON DD.PackID = P.PackID
	WHERE DN.ReceiptID = @madh
END
