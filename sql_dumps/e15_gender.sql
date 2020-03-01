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
-- Table structure for table `e15_gender`
--

DROP TABLE IF EXISTS `e15_gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `e15_gender` (
  `Student Code` int(11) DEFAULT NULL,
  `Gender` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e15_gender`
--

LOCK TABLES `e15_gender` WRITE;
/*!40000 ALTER TABLE `e15_gender` DISABLE KEYS */;
INSERT INTO `e15_gender` VALUES (5334,'Male'),(5335,'Male'),(5336,'Male'),(5337,'Male'),(5338,'Male'),(5339,'Male'),(5340,'Male'),(5341,'Male'),(5342,'Male'),(5343,'Female'),(5344,'Male'),(5345,'Male'),(5346,'Female'),(5347,'Male'),(5348,'Male'),(5349,'Male'),(5350,'Male'),(5351,'Male'),(5352,'Female'),(5353,'Female'),(5354,'Female'),(5355,'Male'),(5356,'Female'),(5357,'Female'),(5358,'Male'),(5359,'Female'),(5360,'Male'),(5361,'Female'),(5362,'Female'),(5363,'Female'),(5364,'Male'),(5365,'Male'),(5366,'Female'),(5367,'Female'),(5368,'Male'),(5369,'Male'),(5370,'Female'),(5371,'Male'),(5372,'Female'),(5373,'Male'),(5374,'Female'),(5375,'Female'),(5376,'Male'),(5377,'Female'),(5378,'Male'),(5379,'Male'),(5380,'Female'),(5381,'Male'),(5382,'Male'),(5383,'Male'),(5384,'Male'),(5385,'Male'),(5386,'Male'),(5387,'Male'),(5388,'Male'),(5389,'Male'),(5390,'Male'),(5391,'Female'),(5392,'Male'),(5393,'Female'),(5394,'Male'),(5395,'Female'),(5396,'Male'),(5397,'Female'),(5398,'Male'),(5399,'Male'),(5400,'Male'),(5401,'Male'),(5402,'Male'),(5403,'Male'),(5404,'Male'),(5405,'Male'),(5406,'Male'),(5407,'Male'),(5408,'Male'),(5409,'Male'),(5410,'Female'),(5411,'Female'),(5412,'Male'),(5413,'Male'),(5414,'Male'),(5415,'Male'),(5416,'Male'),(5417,'Female'),(5418,'Female'),(5419,'Female'),(5420,'Male'),(5421,'Female'),(5422,'Male'),(5423,'Male'),(5424,'Male'),(5425,'Female'),(5426,'Male'),(5427,'Male'),(5428,'Male'),(5429,'Male'),(5430,'Male'),(5431,'Male'),(5432,'Male'),(5433,'Male'),(5434,'Male'),(5435,'Male'),(5436,'Male'),(5437,'Male'),(5438,'Male'),(5439,'Male'),(5440,'Male'),(5441,'Male'),(5442,'Male'),(5443,'Male'),(5444,'Male'),(5445,'Male'),(5446,'Male'),(5447,'Male'),(5448,'Male'),(5449,'Male'),(5450,'Female'),(5451,'Female'),(5452,'Male'),(5453,'Male'),(5454,'Male'),(5455,'Male'),(5456,'Male'),(5457,'Male'),(5458,'Female'),(5459,'Female'),(5460,'Female'),(5461,'Female'),(5462,'Male'),(5463,'Male'),(5464,'Male'),(5465,'Male'),(5466,'Male'),(5467,'Male'),(5468,'Male'),(5469,'Female'),(5470,'Female'),(5471,'Male'),(5472,'Male'),(5473,'Male'),(5474,'Male'),(5475,'Male'),(5476,'Female'),(5477,'Male'),(5478,'Female'),(5479,'Male'),(5480,'Male'),(5481,'Male'),(5482,'Male'),(5483,'Female'),(5484,'Male'),(5485,'Male'),(5486,'Male'),(5487,'Male'),(5488,'Male'),(5489,'Male'),(5490,'Male'),(5491,'Male'),(5492,'Male'),(5493,'Male'),(5494,'Male'),(5495,'Male'),(5496,'Male'),(5497,'Female'),(5498,'Female'),(5499,'Male'),(5500,'Male'),(5501,'Male'),(5502,'Male'),(5503,'Male'),(5504,'Male'),(5505,'Male'),(5506,'Male'),(5507,'Male'),(5508,'Male'),(5509,'Male'),(5510,'Female'),(5511,'Female'),(5512,'Male'),(5513,'Male'),(5514,'Female'),(5515,'Male'),(5516,'Male'),(5517,'Female'),(5518,'Female'),(5519,'Male'),(5520,'Male'),(5521,'Male'),(5522,'Female'),(5523,'Male'),(5524,'Male'),(5525,'Male'),(5526,'Male'),(5527,'Female'),(5528,'Female'),(5529,'Female'),(5530,'Male'),(5531,'Male'),(5532,'Male'),(5533,'Male'),(5534,'Male'),(5535,'Male'),(5536,'Male'),(5537,'Male'),(5538,'Male'),(5539,'Male'),(5540,'Male'),(5541,'Male'),(5542,'Male'),(5543,'Male'),(5544,'Male'),(5545,'Male'),(5546,'Male'),(5547,'Male'),(5548,'Male'),(5549,'Male'),(5550,'Male'),(5551,'Male'),(5552,'Male'),(5553,'Male'),(5554,'Male'),(5555,'Male'),(5556,'Male'),(5557,'Male'),(5558,'Male'),(5559,'Male'),(5560,'Male'),(5561,'Male'),(5562,'Male'),(5563,'Male'),(5564,'Male'),(5565,'Male'),(5566,'Male'),(5567,'Female'),(5568,'Female'),(5569,'Male'),(5570,'Male'),(5571,'Male'),(5572,'Male'),(5573,'Male'),(5574,'Male'),(5575,'Male'),(5576,'Male'),(5577,'Male'),(5578,'Male'),(5579,'Male'),(5580,'Male'),(5581,'Male'),(5582,'Female'),(5583,'Male'),(5584,'Male'),(5585,'Female'),(5586,'Male'),(5587,'Female'),(5588,'Female'),(5589,'Female'),(5590,'Male'),(5591,'Male'),(5592,'Female'),(5593,'Male'),(5594,'Female'),(5595,'Male'),(5596,'Male'),(5597,'Male'),(5598,'Male'),(5599,'Male'),(5600,'Male'),(5601,'Female'),(5602,'Male'),(5603,'Male'),(5604,'Male'),(5605,'Male'),(5606,'Female'),(5607,'Female'),(5608,'Male'),(5609,'Female'),(5610,'Female'),(5611,'Male'),(5612,'Female'),(5613,'Female'),(5614,'Female'),(5615,'Male'),(5616,'Female'),(5617,'Male'),(5618,'Male'),(5619,'Female'),(5620,'Male'),(5621,'Female'),(5622,'Male'),(5623,'Female'),(5624,'Male'),(5625,'Male'),(5626,'Male'),(5627,'Male'),(5628,'Male'),(5629,'Male'),(5630,'Male'),(5631,'Male'),(5632,'Male'),(5633,'Male'),(5634,'Male'),(5635,'Male'),(5636,'Male'),(5637,'Male'),(5638,'Female'),(5639,'Male'),(5640,'Male'),(5641,'Male'),(5642,'Male'),(5643,'Male'),(5644,'Male'),(5645,'Female'),(5646,'Male'),(5647,'Male'),(5648,'Female'),(5649,'Female'),(5650,'Male'),(5651,'Male'),(5652,'Male'),(5653,'Male'),(5654,'Male'),(5655,'Male'),(5656,'Female'),(5657,'Male'),(5658,'Female'),(5659,'Male'),(5660,'Male'),(5661,'Female'),(5662,'Male'),(5663,'Female'),(5664,'Male'),(5665,'Male'),(5666,'Male'),(5667,'Female'),(5668,'Male'),(5669,'Female'),(5670,'Male'),(5671,'Male'),(5672,'Male'),(5673,'Male'),(5674,'Female'),(5675,'Male'),(5676,'Male'),(5677,'Male'),(5678,'Male'),(5679,'Female'),(5680,'Female'),(5681,'Male'),(5682,'Female'),(5683,'Male'),(5684,'Male'),(5685,'Female'),(5686,'Male'),(5687,'Male'),(5688,'Female'),(5689,'Male'),(5690,'Male'),(5691,'Female'),(5692,'Female'),(5693,'Male'),(5694,'Male'),(5695,'Female'),(5696,'Female'),(5697,'Male'),(5698,'Female'),(5699,'Female'),(5700,'Female'),(5701,'Female'),(5702,'Male'),(5703,'Male'),(5704,'Female'),(5705,'Male'),(5706,'Male'),(5707,'Male'),(5708,'Male'),(5709,'Male'),(5710,'Male'),(5711,'Male'),(5712,'Male'),(5713,'Male'),(5714,'Male'),(5715,'Male'),(5716,'Male'),(5717,'Male'),(5718,'Male'),(5719,'Male'),(5720,'Male'),(5721,'Male'),(5722,'Female'),(5723,'Male'),(5724,'Male'),(5725,'Male'),(5726,'Male'),(5727,'Male'),(5728,'Female'),(5729,'Male'),(5730,'Male'),(5731,'Female'),(5732,'Male'),(5733,'Male'),(5734,'Male'),(5735,'Male'),(5736,'Male'),(5737,'Male'),(5738,'Female'),(5739,'Female'),(5740,'Male'),(5741,'Male'),(5743,'Male'),(5744,'Female'),(5745,'Male');
/*!40000 ALTER TABLE `e15_gender` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-29 23:03:56
