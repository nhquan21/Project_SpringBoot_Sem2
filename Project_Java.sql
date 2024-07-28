
--Insert into [accounts]
--Admin Password 123456
INSERT [dbo].[accounts] ([account_id], [active], [address], [birthday], [createdate], [email], [full_name], [gender], [password], [phone], [picture], [user_name]) VALUES (N'1', 1, N'Ha Noi', CAST(N'2000-04-04T00:00:00.0000000' AS DateTime2), CAST(N'2000-04-04T00:00:00.0000000' AS DateTime2), N'quan@gmail.com', N'ADMIN', 1, N'$2a$12$opyUqws3uXum3LcymnWSnOgP54IGp0dhrn6kk5iaGBm81EH5OvuVK', N'1', N'123', N'ADMIN')
INSERT [dbo].[accounts] ([account_id], [active], [address], [birthday], [createdate], [email], [full_name], [gender], [password], [phone], [picture], [user_name]) VALUES (N'cd7', 0, N'ÐÔNG ANH', NULL, NULL, N'qua1n@gmail.com', N'QUÂN', 0, N'$2a$10$wwd1WIw3c28n2mArzbeNyuBtx/AfA7TGF5f86lug1ksoxU1zBzBHW', N'0349287353', NULL, N'quan')
INSERT [dbo].[accounts] ([account_id], [active], [address], [birthday], [createdate], [email], [full_name], [gender], [password], [phone], [picture], [user_name]) VALUES (N'e92', 0, N'ÐÔNG ANH', NULL, NULL, N'qu1an@gmail.com', N'QUÂN', 0, N'$2a$10$MW5kwrWcOWvCF.zFI36ADOSoj.ej.CxN1pI1JllwpoIuD9n0qC64y', N'0349287353', NULL, N'q1')
GO

--Insert into [roles]
INSERT [dbo].[roles] ([role_id], [active], [role_name]) VALUES (1, 1, N'ADMIN')
INSERT [dbo].[roles] ([role_id], [active], [role_name]) VALUES (2, 1, N'ADMIN')
SET IDENTITY_INSERT [dbo].[roles] OFF
GO
--Insert into [account_roles]
SET IDENTITY_INSERT [dbo].[account_roles] ON

INSERT [dbo].[account_roles] ([id], [account_id], [role_id]) VALUES (1, N'1', 1)
INSERT [dbo].[account_roles] ([id], [account_id], [role_id]) VALUES (2, N'cd7', 2)
INSERT [dbo].[account_roles] ([id], [account_id], [role_id]) VALUES (3, N'e92', 2)
SET IDENTITY_INSERT [dbo].[account_roles] OFF
GO
SET IDENTITY_INSERT [dbo].[categories] ON
--Insert into [categories]
INSERT [dbo].[categories] ([category_id], [active], [category_name], [image]) VALUES (3, 1, N'Quần', N'pant.png')
INSERT [dbo].[categories] ([category_id], [active], [category_name], [image]) VALUES (4, 1, N'Áo', N'tshirt.png')
INSERT [dbo].[categories] ([category_id], [active], [category_name], [image]) VALUES (6, 1, N'Váy', N'fashion.png')
INSERT [dbo].[categories] ([category_id], [active], [category_name], [image]) VALUES (7, 1, N'Túi Guốc', N'accessories.png')
INSERT [dbo].[categories] ([category_id], [active], [category_name], [image]) VALUES (8, 1, N'Giày', N'sneakers.png')
INSERT [dbo].[categories] ([category_id], [active], [category_name], [image]) VALUES (9, 1, N'Quần Áo', N'quanao.png')
SET IDENTITY_INSERT [dbo].[categories] OFF
GO

SET IDENTITY_INSERT [dbo].[products] ON
--Insert into [products]
INSERT [dbo].[products] ([product_id], [code], [desciption], [image], [images], [price], [product_name], [sale_price], [category_id]) VALUES (7, N'#11', N'<p>
	1</p>
', N'1-a.jpg', NULL, 500, N'Áo Khoác', 0, 4)
INSERT [dbo].[products] ([product_id], [code], [desciption], [image], [images], [price], [product_name], [sale_price], [category_id]) VALUES (8, N'#134', N'<p>
	1</p>
', N'2.jpg', NULL, 1000, N'Quân áo nữ', 0, 9)
INSERT [dbo].[products] ([product_id], [code], [desciption], [image], [images], [price], [product_name], [sale_price], [category_id]) VALUES (9, N'#124', N'<p>
	1</p>
', N'3.jpg', NULL, 1023, N'Quần áo mùa hè', 0, 9)
INSERT [dbo].[products] ([product_id], [code], [desciption], [image], [images], [price], [product_name], [sale_price], [category_id]) VALUES (10, N'#114', N'<p>
	5</p>
', N'5-a.jpg', NULL, 1200, N'Bộ quần áo nữ blue', 0, 9)
INSERT [dbo].[products] ([product_id], [code], [desciption], [image], [images], [price], [product_name], [sale_price], [category_id]) VALUES (11, N'#193', N'<p>
	1</p>
', N'19.png', NULL, 900, N'Quần bò nữ', 0, 3)
INSERT [dbo].[products] ([product_id], [code], [desciption], [image], [images], [price], [product_name], [sale_price], [category_id]) VALUES (12, N'#127', N'<p>
	1</p>
', N'21.png', NULL, 600, N'Váy bò nữ', 0, 6)
INSERT [dbo].[products] ([product_id], [code], [desciption], [image], [images], [price], [product_name], [sale_price], [category_id]) VALUES (13, N'#130', N'<p>
	1</p>
', N'11-a.jpg', NULL, 650, N'Quần đùi', 0, 3)
INSERT [dbo].[products] ([product_id], [code], [desciption], [image], [images], [price], [product_name], [sale_price], [category_id]) VALUES (14, N'#119', N'<p>
	1</p>
', N'22.png', NULL, 740, N'Áo xẻ tà', 0, 4)
SET IDENTITY_INSERT [dbo].[products] OFF
GO
SET IDENTITY_INSERT [dbo].[roles] ON



SET IDENTITY_INSERT [dbo].[order_details] ON

INSERT [dbo].[order_details] ([id], [price], [quantity], [order_id], [product_id]) VALUES (1, 1000, 1, 1, 8)
SET IDENTITY_INSERT [dbo].[order_details] OFF
GO
SET IDENTITY_INSERT [dbo].[orders] ON

INSERT [dbo].[orders] ([order_id], [active], [address], [email], [name], [order_date], [phone], [account_id]) VALUES (1, 0, N'ÐÔNG ANH', N'qu1an@gmail.com', N'QUÂN', CAST(N'2024-07-27T00:00:00.0000000' AS DateTime2), N'0349287353', N'e92')
SET IDENTITY_INSERT [dbo].[orders] OFF
GO

