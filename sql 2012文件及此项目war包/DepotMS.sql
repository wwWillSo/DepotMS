USE [master]
GO
/****** Object:  Database [DepotMS]    Script Date: 2016/8/24 20:24:31 ******/
CREATE DATABASE [DepotMS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DepotMS', FILENAME = N'E:\Microsoft SQLServer 2012\MSSQL11.MSSQLSERVER\MSSQL\DATA\DepotMS.mdf' , SIZE = 4160KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DepotMS_log', FILENAME = N'E:\Microsoft SQLServer 2012\MSSQL11.MSSQLSERVER\MSSQL\DATA\DepotMS_log.ldf' , SIZE = 1040KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DepotMS] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DepotMS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DepotMS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DepotMS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DepotMS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DepotMS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DepotMS] SET ARITHABORT OFF 
GO
ALTER DATABASE [DepotMS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DepotMS] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [DepotMS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DepotMS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DepotMS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DepotMS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DepotMS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DepotMS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DepotMS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DepotMS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DepotMS] SET  ENABLE_BROKER 
GO
ALTER DATABASE [DepotMS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DepotMS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DepotMS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DepotMS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DepotMS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DepotMS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DepotMS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DepotMS] SET RECOVERY FULL 
GO
ALTER DATABASE [DepotMS] SET  MULTI_USER 
GO
ALTER DATABASE [DepotMS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DepotMS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DepotMS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DepotMS] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [DepotMS]
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Admin](
	[adminId] [varchar](255) NOT NULL,
	[password] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[adminId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ArrangeInfo]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ArrangeInfo](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[dayArrangeId] [int] NULL,
	[driverId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Car]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Car](
	[carNo] [varchar](255) NOT NULL,
	[brand] [varchar](255) NULL,
	[date_insurance] [varchar](255) NULL,
	[date_register] [varchar](255) NULL,
	[drivingLicenseNo] [varchar](255) NULL,
	[drivingLicensePhoto] [varchar](255) NULL,
	[seat] [varchar](255) NULL,
	[classesId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[carNo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Classes]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Classes](
	[classesId] [int] NOT NULL,
	[note] [varchar](255) NULL,
	[time] [varchar](255) NULL,
	[lineId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[classesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DayArrange]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DayArrange](
	[dayArrangeId] [int] NOT NULL,
	[date] [datetime2](7) NULL,
	[weekArrangeId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[dayArrangeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Dept]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Dept](
	[deptNo] [int] NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[deptNo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Driver]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Driver](
	[id] [int] NOT NULL,
	[driverLicenseNo] [varchar](255) NULL,
	[driverLicensePhoto] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[carId] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Line]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Line](
	[lineId] [int] NOT NULL,
	[lineName] [varchar](255) NULL,
	[population] [int] NULL,
	[rate] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[lineId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MonthArrange]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MonthArrange](
	[monthArrangeId] [int] NOT NULL,
	[date_begin] [datetime2](7) NULL,
	[date_end] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[monthArrangeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Staff]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Staff](
	[staffNo] [int] NOT NULL,
	[groupName] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[deptNo] [int] NOT NULL,
	[stationId] [int] NULL,
	[home] [varchar](255) NULL,
	[classesId] [int] NULL,
	[home_latitude] [float] NULL,
	[home_longitude] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[staffNo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Station]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Station](
	[stationId] [int] IDENTITY(1,1) NOT NULL,
	[latitude] [varchar](255) NULL,
	[longitude] [varchar](255) NULL,
	[stationAddress] [varchar](255) NULL,
	[stationName] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[stationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[StationOfLine]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StationOfLine](
	[stationOfLineId] [int] IDENTITY(1,1) NOT NULL,
	[population] [int] NOT NULL,
	[lineId] [int] NOT NULL,
	[stationId] [int] NOT NULL,
	[step] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[stationOfLineId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[WeekArrange]    Script Date: 2016/8/24 20:24:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WeekArrange](
	[weekArrangeId] [int] NOT NULL,
	[date_begin] [datetime2](7) NULL,
	[date_end] [datetime2](7) NULL,
	[monthArrangeId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[weekArrangeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[StationOfLine] ADD  DEFAULT ((1)) FOR [step]
GO
ALTER TABLE [dbo].[ArrangeInfo]  WITH CHECK ADD  CONSTRAINT [FK_d9e9o7yu21iock86mbn9vhl0s] FOREIGN KEY([driverId])
REFERENCES [dbo].[Driver] ([id])
GO
ALTER TABLE [dbo].[ArrangeInfo] CHECK CONSTRAINT [FK_d9e9o7yu21iock86mbn9vhl0s]
GO
ALTER TABLE [dbo].[ArrangeInfo]  WITH CHECK ADD  CONSTRAINT [FK_oh3i2r7r2jgc8ix895fdb82hm] FOREIGN KEY([dayArrangeId])
REFERENCES [dbo].[DayArrange] ([dayArrangeId])
GO
ALTER TABLE [dbo].[ArrangeInfo] CHECK CONSTRAINT [FK_oh3i2r7r2jgc8ix895fdb82hm]
GO
ALTER TABLE [dbo].[Car]  WITH CHECK ADD  CONSTRAINT [FK_ayx3ol7enwhshr1loymro2a4p] FOREIGN KEY([classesId])
REFERENCES [dbo].[Classes] ([classesId])
GO
ALTER TABLE [dbo].[Car] CHECK CONSTRAINT [FK_ayx3ol7enwhshr1loymro2a4p]
GO
ALTER TABLE [dbo].[Classes]  WITH CHECK ADD  CONSTRAINT [FK_4aa01lr9m26nnkmc1esc9uyus] FOREIGN KEY([lineId])
REFERENCES [dbo].[Line] ([lineId])
GO
ALTER TABLE [dbo].[Classes] CHECK CONSTRAINT [FK_4aa01lr9m26nnkmc1esc9uyus]
GO
ALTER TABLE [dbo].[DayArrange]  WITH CHECK ADD  CONSTRAINT [FK_1d6uhj1nyux3mxdsdgifcfieu] FOREIGN KEY([weekArrangeId])
REFERENCES [dbo].[WeekArrange] ([weekArrangeId])
GO
ALTER TABLE [dbo].[DayArrange] CHECK CONSTRAINT [FK_1d6uhj1nyux3mxdsdgifcfieu]
GO
ALTER TABLE [dbo].[Driver]  WITH CHECK ADD  CONSTRAINT [FK_44kegl3gkqqx07fk88gl57l4i] FOREIGN KEY([carId])
REFERENCES [dbo].[Car] ([carNo])
GO
ALTER TABLE [dbo].[Driver] CHECK CONSTRAINT [FK_44kegl3gkqqx07fk88gl57l4i]
GO
ALTER TABLE [dbo].[Staff]  WITH CHECK ADD  CONSTRAINT [FK_8bc8mxrim9mqlfjfyxvwdnlsd] FOREIGN KEY([stationId])
REFERENCES [dbo].[Station] ([stationId])
GO
ALTER TABLE [dbo].[Staff] CHECK CONSTRAINT [FK_8bc8mxrim9mqlfjfyxvwdnlsd]
GO
ALTER TABLE [dbo].[Staff]  WITH CHECK ADD  CONSTRAINT [FK_oaanr65ys7uay1up9k2pjngl] FOREIGN KEY([classesId])
REFERENCES [dbo].[Classes] ([classesId])
GO
ALTER TABLE [dbo].[Staff] CHECK CONSTRAINT [FK_oaanr65ys7uay1up9k2pjngl]
GO
ALTER TABLE [dbo].[Staff]  WITH CHECK ADD  CONSTRAINT [FK_t0kqapuk98nyd2kwy3as5od8y] FOREIGN KEY([deptNo])
REFERENCES [dbo].[Dept] ([deptNo])
GO
ALTER TABLE [dbo].[Staff] CHECK CONSTRAINT [FK_t0kqapuk98nyd2kwy3as5od8y]
GO
ALTER TABLE [dbo].[StationOfLine]  WITH CHECK ADD  CONSTRAINT [FK_79jyvrmcu88h9t4hy97hen99g] FOREIGN KEY([lineId])
REFERENCES [dbo].[Line] ([lineId])
GO
ALTER TABLE [dbo].[StationOfLine] CHECK CONSTRAINT [FK_79jyvrmcu88h9t4hy97hen99g]
GO
ALTER TABLE [dbo].[StationOfLine]  WITH CHECK ADD  CONSTRAINT [FK_osq4xiq6nws49h8gmfewgm9wh] FOREIGN KEY([stationId])
REFERENCES [dbo].[Station] ([stationId])
GO
ALTER TABLE [dbo].[StationOfLine] CHECK CONSTRAINT [FK_osq4xiq6nws49h8gmfewgm9wh]
GO
ALTER TABLE [dbo].[WeekArrange]  WITH CHECK ADD  CONSTRAINT [FK_m5ur353mugvssebej7hsb8m5x] FOREIGN KEY([monthArrangeId])
REFERENCES [dbo].[MonthArrange] ([monthArrangeId])
GO
ALTER TABLE [dbo].[WeekArrange] CHECK CONSTRAINT [FK_m5ur353mugvssebej7hsb8m5x]
GO
USE [master]
GO
ALTER DATABASE [DepotMS] SET  READ_WRITE 
GO
