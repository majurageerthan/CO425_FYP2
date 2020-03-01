-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: original_data2_0
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `e16_gender`
--

DROP TABLE IF EXISTS `e16_gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `e16_gender` (
  `User Code` int(11) DEFAULT NULL,
  `Gender` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e16_gender`
--

LOCK TABLES `e16_gender` WRITE;
/*!40000 ALTER TABLE `e16_gender` DISABLE KEYS */;
INSERT INTO `e16_gender` VALUES (5928,'Male'),(5929,'Male'),(5930,'Male'),(5931,'Female'),(5932,'Male'),(5933,'Male'),(5934,'Male'),(5935,'Male'),(5936,'Male'),(5937,'Male'),(5938,'Male'),(5939,'Female'),(5940,'Male'),(5941,'Male'),(5942,'Male'),(5943,'Male'),(5944,'Male'),(5945,'Male'),(5946,'Male'),(5947,'Male'),(5948,'Male'),(5949,'Female'),(5950,'Male'),(5951,'Female'),(5952,'Female'),(5953,'Female'),(5954,'Female'),(5955,'Female'),(5956,'Male'),(5957,'Male'),(5958,'Male'),(5959,'Male'),(5960,'Male'),(5961,'Male'),(5962,'Male'),(5963,'Male'),(5964,'Male'),(5965,'Female'),(5966,'Male'),(5967,'Male'),(5968,'Female'),(5969,'Male'),(5970,'Male'),(5971,'Male'),(5972,'Male'),(5973,'Female'),(5974,'Female'),(5975,'Male'),(5976,'Male'),(5977,'Male'),(5978,'Male'),(5979,'Male'),(5980,'Male'),(5981,'Female'),(5982,'Female'),(5983,'Male'),(5984,'Male'),(5985,'Male'),(5986,'Male'),(5987,'Male'),(5988,'Male'),(5989,'Male'),(5990,'Male'),(5991,'Male'),(5992,'Female'),(5993,'Male'),(5994,'Male'),(5995,'Female'),(5996,'Male'),(5997,'Female'),(5998,'Male'),(5999,'Female'),(6000,'Male'),(6001,'Male'),(6002,'Male'),(6003,'Male'),(6004,'Male'),(6005,'Male'),(6006,'Female'),(6007,'Male'),(6008,'Male'),(6009,'Male'),(6010,'Female'),(6011,'Female'),(6012,'Male'),(6013,'Male'),(6014,'Male'),(6015,'Female'),(6016,'Male'),(6017,'Male'),(6018,'Male'),(6019,'Male'),(6020,'Female'),(6021,'Female'),(6022,'Female'),(6023,'Male'),(6024,'Male'),(6025,'Male'),(6026,'Male'),(6027,'Female'),(6028,'Female'),(6029,'Female'),(6030,'Male'),(6031,'Male'),(6032,'Male'),(6033,'Male'),(6034,'Male'),(6035,'Male'),(6036,'Male'),(6037,'Male'),(6038,'Male'),(6039,'Male'),(6040,'Female'),(6041,'Male'),(6042,'Male'),(6043,'Male'),(6044,'Male'),(6045,'Male'),(6046,'Male'),(6047,'Male'),(6048,'Male'),(6049,'Male'),(6050,'Male'),(6051,'Male'),(6052,'Male'),(6053,'Male'),(6054,'Female'),(6055,'Male'),(6056,'Male'),(6057,'Male'),(6058,'Female'),(6059,'Male'),(6060,'Male'),(6061,'Female'),(6062,'Male'),(6063,'Female'),(6064,'Male'),(6065,'Female'),(6066,'Male'),(6067,'Male'),(6068,'Male'),(6069,'Male'),(6070,'Male'),(6071,'Male'),(6072,'Male'),(6073,'Male'),(6074,'Male'),(6075,'Male'),(6076,'Female'),(6077,'Male'),(6078,'Male'),(6079,'Male'),(6080,'Male'),(6081,'Male'),(6082,'Male'),(6083,'Male'),(6084,'Male'),(6085,'Male'),(6086,'Male'),(6087,'Male'),(6088,'Male'),(6089,'Female'),(6090,'Male'),(6091,'Male'),(6092,'Male'),(6093,'Female'),(6094,'Male'),(6095,'Female'),(6096,'Female'),(6097,'Female'),(6098,'Male'),(6099,'Female'),(6100,'Female'),(6101,'Female'),(6102,'Male'),(6103,'Male'),(6104,'Male'),(6105,'Female'),(6106,'Female'),(6107,'Male'),(6108,'Female'),(6109,'Male'),(6110,'Male'),(6111,'Male'),(6112,'Male'),(6113,'Male'),(6114,'Male'),(6115,'Male'),(6116,'Male'),(6117,'Female'),(6118,'Male'),(6119,'Female'),(6120,'Male'),(6121,'Female'),(6122,'Male'),(6123,'Female'),(6124,'Male'),(6125,'Male'),(6126,'Male'),(6127,'Female'),(6128,'Female'),(6129,'Male'),(6130,'Female'),(6131,'Female'),(6132,'Male'),(6133,'Male'),(6134,'Male'),(6135,'Male'),(6136,'Male'),(6137,'Male'),(6138,'Male'),(6139,'Male'),(6140,'Male'),(6141,'Male'),(6142,'Female'),(6143,'Male'),(6144,'Male'),(6145,'Male'),(6146,'Female'),(6147,'Male'),(6148,'Male'),(6149,'Male'),(6150,'Female'),(6151,'Male'),(6152,'Male'),(6153,'Female'),(6154,'Male'),(6155,'Male'),(6156,'Male'),(6157,'Male'),(6158,'Male'),(6159,'Male'),(6160,'Male'),(6161,'Male'),(6162,'Male'),(6163,'Male'),(6164,'Male'),(6165,'Female'),(6166,'Female'),(6167,'Male'),(6168,'Male'),(6169,'Male'),(6170,'Male'),(6171,'Male'),(6172,'Female'),(6173,'Male'),(6174,'Male'),(6175,'Male'),(6176,'Female'),(6177,'Male'),(6178,'Male'),(6179,'Male'),(6180,'Male'),(6181,'Male'),(6182,'Male'),(6183,'Male'),(6184,'Female'),(6185,'Male'),(6186,'Male'),(6187,'Female'),(6188,'Male'),(6189,'Male'),(6190,'Male'),(6191,'Female'),(6192,'Male'),(6193,'Female'),(6194,'Male'),(6195,'Male'),(6196,'Female'),(6197,'Male'),(6198,'Female'),(6199,'Male'),(6200,'Male'),(6201,'Male'),(6202,'Male'),(6203,'Female'),(6204,'Female'),(6205,'Male'),(6206,'Female'),(6207,'Male'),(6208,'Male'),(6209,'Male'),(6210,'Female'),(6211,'Female'),(6212,'Female'),(6213,'Female'),(6214,'Female'),(6215,'Male'),(6216,'Male'),(6217,'Male'),(6218,'Female'),(6219,'Female'),(6220,'Female'),(6221,'Male'),(6222,'Female'),(6223,'Male'),(6224,'Male'),(6225,'Female'),(6226,'Male'),(6227,'Female'),(6228,'Male'),(6229,'Male'),(6230,'Male'),(6231,'Male'),(6232,'Male'),(6233,'Male'),(6234,'Male'),(6235,'Male'),(6236,'Male'),(6237,'Male'),(6238,'Male'),(6239,'Male'),(6240,'Male'),(6241,'Male'),(6242,'Male'),(6243,'Male'),(6244,'Male'),(6245,'Male'),(6246,'Male'),(6247,'Male'),(6248,'Male'),(6249,'Male'),(6250,'Male'),(6251,'Male'),(6252,'Male'),(6253,'Male'),(6254,'Male'),(6255,'Male'),(6256,'Male'),(6257,'Male'),(6258,'Male'),(6259,'Male'),(6260,'Male'),(6261,'Male'),(6262,'Male'),(6263,'Male'),(6264,'Male'),(6265,'Male'),(6266,'Male'),(6267,'Male'),(6268,'Male'),(6269,'Male'),(6270,'Male'),(6271,'Male'),(6272,'Male'),(6273,'Female'),(6274,'Male'),(6275,'Female'),(6276,'Female'),(6277,'Male'),(6278,'Female'),(6279,'Male'),(6280,'Male'),(6281,'Female'),(6282,'Female'),(6283,'Male'),(6284,'Male'),(6285,'Male'),(6286,'Male'),(6287,'Male'),(6288,'Male'),(6289,'Male'),(6290,'Male'),(6291,'Male'),(6292,'Male'),(6293,'Male'),(6294,'Male'),(6295,'Male'),(6296,'Male'),(6297,'Male'),(6298,'Male'),(6299,'Male'),(6300,'Male'),(6301,'Male'),(6302,'Male'),(6303,'Male'),(6304,'Male'),(6305,'Male'),(6306,'Male'),(6307,'Male'),(6308,'Male'),(6309,'Female'),(6310,'Male'),(6311,'Female'),(6312,'Male'),(6313,'Male'),(6314,'Female'),(6315,'Female'),(6316,'Male'),(6317,'Female'),(6318,'Male'),(6319,'Male'),(6320,'Male'),(6321,'Female'),(6322,'Male'),(6323,'Male'),(6324,'Female'),(6325,'Male'),(6326,'Female'),(6327,'Male'),(6328,'Female'),(6329,'Male'),(6332,'Male'),(6333,'Female'),(6334,'Male'),(6335,'Male'),(6336,'Male'),(6337,'Male'),(6338,'Male'),(6339,'Male'),(6340,'Male'),(6341,'Male'),(6342,'Female'),(6343,'Female'),(6384,'Male');
/*!40000 ALTER TABLE `e16_gender` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-29 23:04:01
