# Changelog
Changelog of GitLab4J API.

## gitlab4j-api-4.10.6
### GitHub [#327](https://github.com/gmessner/gitlab4j-api/issues/327) Found banned dependency while wrapping api into a Jenkins plugin    *enhancement*  

**Added javax.activation dependency for Java 9+ support and added maven enforcer plugin (#327).**


[7b1e07758410dbf](https://github.com/gmessner/gitlab4j-api/commit/7b1e07758410dbf) Greg Messner *2019-04-23 20:21:20*


### No issue

**Bumped version.**


[ed81013247f0e3f](https://github.com/gmessner/gitlab4j-api/commit/ed81013247f0e3f) Greg Messner *2019-04-23 20:21:11*

**Added mvn wrapper related entries.**


[6e3bbb447a36a72](https://github.com/gmessner/gitlab4j-api/commit/6e3bbb447a36a72) Greg Messner *2019-04-23 19:50:59*

**Added Proxy-Authorization to default masked header names.**


[f9fc81d93dd5f67](https://github.com/gmessner/gitlab4j-api/commit/f9fc81d93dd5f67) Greg Messner *2019-04-23 19:50:17*

**Initial commit.**


[7122c0c004d6ec0](https://github.com/gmessner/gitlab4j-api/commit/7122c0c004d6ec0) Greg Messner *2019-04-23 19:49:40*

**Now limits number of events read during testing.**


[a6cdbbbc80cf3b7](https://github.com/gmessner/gitlab4j-api/commit/a6cdbbbc80cf3b7) Greg Messner *2019-04-23 19:48:32*


## gitlab4j-api-4.10.5
### GitHub [#334](https://github.com/gmessner/gitlab4j-api/issues/334) handle System Hook error `Could not resolve type id &#39;user_failed_login&#39; as a subtype of`    *bug*  *enhancement*  

**Added support for user_failed_login system hook event (#334).**


[4ce3487e9c786e1](https://github.com/gmessner/gitlab4j-api/commit/4ce3487e9c786e1) Greg Messner *2019-04-23 03:05:23*

**Initial commit (#334).**


[63587b4788042aa](https://github.com/gmessner/gitlab4j-api/commit/63587b4788042aa) Greg Messner *2019-04-23 03:03:47*


### No issue

**Fixed issue with since dates being too old.**


[7268fef0bcb50aa](https://github.com/gmessner/gitlab4j-api/commit/7268fef0bcb50aa) Greg Messner *2019-04-23 03:53:42*

**Bumped version.**


[0cc484fb9309c87](https://github.com/gmessner/gitlab4j-api/commit/0cc484fb9309c87) Greg Messner *2019-04-23 03:05:56*

**Generated latest changelog.**


[51bd64302d3581b](https://github.com/gmessner/gitlab4j-api/commit/51bd64302d3581b) Greg Messner *2019-04-22 15:02:27*


## gitlab4j-api-4.10.4
### GitHub [#310](https://github.com/gmessner/gitlab4j-api/issues/310) Mask secrets    *enhancement*  

**Mods to use new MaskingLoggingFilter (#310).**


[bdbfe753a863efb](https://github.com/gmessner/gitlab4j-api/commit/bdbfe753a863efb) Greg Messner *2019-04-22 03:42:13*

**Added tests for new logging mechanism which uses MaskingLoggingFilter (#310).**


[2acc7984f25d1f8](https://github.com/gmessner/gitlab4j-api/commit/2acc7984f25d1f8) Greg Messner *2019-04-22 03:41:57*

**Initial commit (#310).**


[f027fd444037977](https://github.com/gmessner/gitlab4j-api/commit/f027fd444037977) Greg Messner *2019-04-22 03:39:56*


### No issue

**Remove code that disabled logging.**


[05f056bb285da3f](https://github.com/gmessner/gitlab4j-api/commit/05f056bb285da3f) Greg Messner *2019-04-22 03:40:50*

**Bumber version and added content for new logging.**


[b66a0517929a0fd](https://github.com/gmessner/gitlab4j-api/commit/b66a0517929a0fd) Greg Messner *2019-04-22 03:40:21*

**Added info on PackagesApi.**


[77cd763f6fbd9b2](https://github.com/gmessner/gitlab4j-api/commit/77cd763f6fbd9b2) Greg Messner *2019-04-19 23:16:38*

**Generated latest changelog.**


[1e06a3ff086f04a](https://github.com/gmessner/gitlab4j-api/commit/1e06a3ff086f04a) Greg Messner *2019-04-18 12:53:00*


## gitlab4j-api-4.10.3
### GitHub [#333](https://github.com/gmessner/gitlab4j-api/issues/333) Fix date parser in ISO8601    *bug*  

**Added tests for UTC zone name (#333).**


[ac7392928c120a2](https://github.com/gmessner/gitlab4j-api/commit/ac7392928c120a2) Greg Messner *2019-04-18 12:36:47*

**Now properly handles dates using UTC zone name (#333).**


[0824d6c9b1b3411](https://github.com/gmessner/gitlab4j-api/commit/0824d6c9b1b3411) Greg Messner *2019-04-18 12:36:23*


### No issue

**Bumped version.**


[302d53b78dc7191](https://github.com/gmessner/gitlab4j-api/commit/302d53b78dc7191) Greg Messner *2019-04-18 12:37:19*


## gitlab4j-api-4.10.2
### GitHub [#332](https://github.com/gmessner/gitlab4j-api/issues/332) Does the system hook api support the merge request?    *enhancement*  

**Added testing of SystemHookManager.handleEvent() (#332).**


[cbd070d592c3d72](https://github.com/gmessner/gitlab4j-api/commit/cbd070d592c3d72) Greg Messner *2019-04-18 04:33:39*

**Made handling of merge_request event more resilient (#332).**


[afdd3ef30bb3d70](https://github.com/gmessner/gitlab4j-api/commit/afdd3ef30bb3d70) Greg Messner *2019-04-18 04:32:54*


### No issue

**Bumped version.**


[319527c8ffc62da](https://github.com/gmessner/gitlab4j-api/commit/319527c8ffc62da) Greg Messner *2019-04-18 04:34:19*

**Added event_type property.**


[74d939480966b81](https://github.com/gmessner/gitlab4j-api/commit/74d939480966b81) Greg Messner *2019-04-17 22:47:20*

**Updated Jersey version to 2.28**


[f985c6813011bbf](https://github.com/gmessner/gitlab4j-api/commit/f985c6813011bbf) Greg Messner *2019-04-17 19:35:43*


## gitlab4j-api-4.10.1
### GitHub [#332](https://github.com/gmessner/gitlab4j-api/issues/332) Does the system hook api support the merge request?    *enhancement*  

**Fixed issue unmarhalling merge_request system hook events (#332).**


[0c31bd4332db0f5](https://github.com/gmessner/gitlab4j-api/commit/0c31bd4332db0f5) Greg Messner *2019-04-17 15:20:46*

**Reorganized and added new system hook event tests (#332).**


[04a7698018dd839](https://github.com/gmessner/gitlab4j-api/commit/04a7698018dd839) Greg Messner *2019-04-17 15:19:50*


### No issue

**Bumped version.**


[9c9df1b7ec93d5e](https://github.com/gmessner/gitlab4j-api/commit/9c9df1b7ec93d5e) Greg Messner *2019-04-17 15:21:22*


## gitlab4j-api-4.10.0
### GitHub [#327](https://github.com/gmessner/gitlab4j-api/issues/327) Found banned dependency while wrapping api into a Jenkins plugin    *enhancement*  

**Removed use of JAXB (#327).**


[45ff580c7ddafb6](https://github.com/gmessner/gitlab4j-api/commit/45ff580c7ddafb6) Greg Messner *2019-04-17 05:54:37*

**Removed use of JAXB DatatypeConverter (#327).**


[994493d1b3c76e5](https://github.com/gmessner/gitlab4j-api/commit/994493d1b3c76e5) Greg Messner *2019-04-17 05:52:58*

**Added testing of more variations of date formats (#327).**


[202355e8c28f14a](https://github.com/gmessner/gitlab4j-api/commit/202355e8c28f14a) Greg Messner *2019-04-17 05:52:16*


### GitHub [#332](https://github.com/gmessner/gitlab4j-api/issues/332) Does the system hook api support the merge request?    *enhancement*  

**Added methods to unmarshal from JsonNode (#332).**


[7a0906fdba89cfa](https://github.com/gmessner/gitlab4j-api/commit/7a0906fdba89cfa) Greg Messner *2019-04-17 05:53:54*

**Added support for merge_request system hook (#332).**


[82c1deea32983e6](https://github.com/gmessner/gitlab4j-api/commit/82c1deea32983e6) Greg Messner *2019-04-17 05:51:05*

**Initial commit (#332).**


[44b47f3ab9bab97](https://github.com/gmessner/gitlab4j-api/commit/44b47f3ab9bab97) Greg Messner *2019-04-17 05:46:00*


### No issue

**Bumped version to 4.10.0**


[34ff9512b7aaac5](https://github.com/gmessner/gitlab4j-api/commit/34ff9512b7aaac5) Greg Messner *2019-04-17 05:57:01*

**Bumped version to 4.10.0.**


[4eb2c45eab3f106](https://github.com/gmessner/gitlab4j-api/commit/4eb2c45eab3f106) Greg Messner *2019-04-17 05:56:19*

**Fixed grammar in Stream section.**


[043521ac4a8c935](https://github.com/gmessner/gitlab4j-api/commit/043521ac4a8c935) Greg Messner *2019-04-12 18:24:30*

**Generated latest changelog.**


[b9728a35f57d939](https://github.com/gmessner/gitlab4j-api/commit/b9728a35f57d939) Greg Messner *2019-04-12 17:13:19*


## gitlab4j-api-4.9.23
### GitHub [#325](https://github.com/gmessner/gitlab4j-api/issues/325) Add labels support to IssueEvent returned via Webhook    *enhancement*  

**Fixed webhooks changes deserialization (#325, #331).**


[2ce86a2fe5f8435](https://github.com/gmessner/gitlab4j-api/commit/2ce86a2fe5f8435) Greg Messner *2019-04-12 02:23:25*

**Initial commit (#325, #331).**


[01f1ed12ff9f415](https://github.com/gmessner/gitlab4j-api/commit/01f1ed12ff9f415) Greg Messner *2019-04-12 02:12:48*


### GitHub [#331](https://github.com/gmessner/gitlab4j-api/issues/331) Cannot deserialize instance of java.util.ArrayList out of START_OBJECT token  

**Fixed webhooks changes deserialization (#325, #331).**


[2ce86a2fe5f8435](https://github.com/gmessner/gitlab4j-api/commit/2ce86a2fe5f8435) Greg Messner *2019-04-12 02:23:25*

**Initial commit (#325, #331).**


[01f1ed12ff9f415](https://github.com/gmessner/gitlab4j-api/commit/01f1ed12ff9f415) Greg Messner *2019-04-12 02:12:48*


### No issue

**Bumped version.**


[b8206c726f4b600](https://github.com/gmessner/gitlab4j-api/commit/b8206c726f4b600) Greg Messner *2019-04-12 02:23:08*

**Generated latest changelog.**


[35773f404a96981](https://github.com/gmessner/gitlab4j-api/commit/35773f404a96981) Greg Messner *2019-04-11 02:00:56*


## gitlab4j-api-4.9.22
### GitHub [#316](https://github.com/gmessner/gitlab4j-api/issues/316) Adding Issue Boards Api    *enhancement*  

**Added support for creating, updating, and deleting an issue board (#316).**


[04561b0f4d50e4c](https://github.com/gmessner/gitlab4j-api/commit/04561b0f4d50e4c) Greg Messner *2019-04-11 01:34:17*


### GitHub [#328](https://github.com/gmessner/gitlab4j-api/issues/328) commitsApi.getCommit(projectId, branchName) fails with 404 if slash in branch name    *bug*  

**Now URL encodes the sha when fetching a single commit (#328).**


[f6833a4b89619c4](https://github.com/gmessner/gitlab4j-api/commit/f6833a4b89619c4) Greg Messner *2019-04-10 04:51:58*


### GitHub [#330](https://github.com/gmessner/gitlab4j-api/issues/330) Support Group-level and Project-level variables.    *enhancement*  

**Added createVariable() and updateVariable() methods without the environmentScope param (#330).**


[8deffb3ed63d0b3](https://github.com/gmessner/gitlab4j-api/commit/8deffb3ed63d0b3) Greg Messner *2019-04-11 01:36:23*

**Added support for group and project variables (#330).**


[97f457253248e2f](https://github.com/gmessner/gitlab4j-api/commit/97f457253248e2f) Greg Messner *2019-04-10 21:05:04*


### No issue

**Bumped version.**


[7a08a31ce39c277](https://github.com/gmessner/gitlab4j-api/commit/7a08a31ce39c277) Greg Messner *2019-04-10 21:04:06*

**Added maskPrivateToken() method.**


[0e6edfb4215e11e](https://github.com/gmessner/gitlab4j-api/commit/0e6edfb4215e11e) Greg Messner *2019-04-10 04:52:19*

**Updated with latest commits.**


[fa03dbd2964c492](https://github.com/gmessner/gitlab4j-api/commit/fa03dbd2964c492) Greg Messner *2019-04-06 19:58:56*


## gitlab4j-api-4.9.21
### GitHub [#316](https://github.com/gmessner/gitlab4j-api/issues/316) Adding Issue Boards Api    *enhancement*  

**Added support for Issue Boards API (#316).**


[a49ba426e59269c](https://github.com/gmessner/gitlab4j-api/commit/a49ba426e59269c) Greg Messner *2019-04-06 19:44:49*

**Initial commit (#316).**


[0af6603e8e9e29a](https://github.com/gmessner/gitlab4j-api/commit/0af6603e8e9e29a) Greg Messner *2019-04-06 19:44:07*


## gitlab4j-api-4.9.20
### GitHub [#322](https://github.com/gmessner/gitlab4j-api/issues/322) Missing straight parameter for repositories&#39; compare method    *enhancement*  

**Added compare() method that takes a straight parameter (#322).**


[575045c536135ac](https://github.com/gmessner/gitlab4j-api/commit/575045c536135ac) Greg Messner *2019-04-05 00:15:50*


### GitHub [#323](https://github.com/gmessner/gitlab4j-api/issues/323) merge_commit_sha field is missing in merge request event  

**Fix #323 merge_commit_sha field is missing in merge request event (#324)**

 * Added EventMergeRequest.mergeCommitSha field (#323)

[9557858890f1f2b](https://github.com/gmessner/gitlab4j-api/commit/9557858890f1f2b) Adam Lesiak *2019-04-04 15:09:54*


### GitHub [#324](https://github.com/gmessner/gitlab4j-api/pull/324) Fix #323 merge_commit_sha field is missing in merge request event  

**Fix #323 merge_commit_sha field is missing in merge request event (#324)**

 * Added EventMergeRequest.mergeCommitSha field (#323)

[9557858890f1f2b](https://github.com/gmessner/gitlab4j-api/commit/9557858890f1f2b) Adam Lesiak *2019-04-04 15:09:54*


### GitHub [#325](https://github.com/gmessner/gitlab4j-api/issues/325) Add labels support to IssueEvent returned via Webhook    *enhancement*  

**Added support for labels in webhook events (#325).**


[1ee472c25e109a3](https://github.com/gmessner/gitlab4j-api/commit/1ee472c25e109a3) Greg Messner *2019-04-06 04:18:42*

**Initial commit (#325).**


[5211cded8eedd3d](https://github.com/gmessner/gitlab4j-api/commit/5211cded8eedd3d) Greg Messner *2019-04-06 04:18:01*


### GitHub [#326](https://github.com/gmessner/gitlab4j-api/issues/326) Support variables in PipelineSchedule    *enhancement*  

**Added support for pipeline schedule variables (#326).**


[ec84b49ebf375a2](https://github.com/gmessner/gitlab4j-api/commit/ec84b49ebf375a2) Greg Messner *2019-04-05 22:10:40*

**Initial commit (#326).**


[e82dfb0d40a4627](https://github.com/gmessner/gitlab4j-api/commit/e82dfb0d40a4627) Greg Messner *2019-04-05 21:59:59*


### No issue

**Bumped version.**


[13a1ad8a0272b36](https://github.com/gmessner/gitlab4j-api/commit/13a1ad8a0272b36) Greg Messner *2019-04-06 04:27:16*

**Updated with latest release.**


[537849206e38b20](https://github.com/gmessner/gitlab4j-api/commit/537849206e38b20) Greg Messner *2019-03-22 14:59:36*


## gitlab4j-api-4.9.19
### GitHub [#317](https://github.com/gmessner/gitlab4j-api/issues/317) Package API    *enhancement*  

**Added support for the Packages API (#317).**


[87ea8f6dc79d17a](https://github.com/gmessner/gitlab4j-api/commit/87ea8f6dc79d17a) Greg Messner *2019-03-20 06:44:55*

**Initial commit (#317).**


[4080db209da2d6c](https://github.com/gmessner/gitlab4j-api/commit/4080db209da2d6c) Greg Messner *2019-03-20 06:42:37*


### GitHub [#318](https://github.com/gmessner/gitlab4j-api/issues/318) Pipeline schedules Api    *enhancement*  

**Fixed tests so they would reliably execute (#318).**


[85137e736c1ef58](https://github.com/gmessner/gitlab4j-api/commit/85137e736c1ef58) Greg Messner *2019-03-22 06:52:54*

**Renamed modifyPipelineSchedule() to updatePipelineSchedule() to follow library naming conventions (#318).**


[02c1995c9a32e70](https://github.com/gmessner/gitlab4j-api/commit/02c1995c9a32e70) Greg Messner *2019-03-22 06:51:10*


### GitHub [#319](https://github.com/gmessner/gitlab4j-api/pull/319) Update with pipeline schedule support  

**Update with pipeline schedule support (#319)**

 * pipeline schedule support
 * add test for json structure of pipeline-schedule

[88ec32b9ab9f807](https://github.com/gmessner/gitlab4j-api/commit/88ec32b9ab9f807) lpiet *2019-03-19 05:21:11*


### GitHub [#320](https://github.com/gmessner/gitlab4j-api/issues/320) could WebHook entity add toString ?    *enhancement*  

**Implemented toString() (#320).**


[2f2d2b45048c5f6](https://github.com/gmessner/gitlab4j-api/commit/2f2d2b45048c5f6) Greg Messner *2019-03-19 15:38:47*


### GitHub [#321](https://github.com/gmessner/gitlab4j-api/issues/321) Support variables param in createPipeline    *enhancement*  

**Added createPipeline() method that takes the variables param (#321).**


[74588bc739c2507](https://github.com/gmessner/gitlab4j-api/commit/74588bc739c2507) Greg Messner *2019-03-20 06:46:17*


### No issue

**Bumped version.**


[e75749c918fdac3](https://github.com/gmessner/gitlab4j-api/commit/e75749c918fdac3) Greg Messner *2019-03-22 06:53:57*

**Removed unused param in delete call.**


[0e96088c0bc7366](https://github.com/gmessner/gitlab4j-api/commit/0e96088c0bc7366) Greg Messner *2019-03-20 06:43:45*

**Generated latest changelog.**


[fce0863f4d61f96](https://github.com/gmessner/gitlab4j-api/commit/fce0863f4d61f96) Greg Messner *2019-03-06 16:25:28*


## gitlab4j-api-4.9.18
### GitHub [#302](https://github.com/gmessner/gitlab4j-api/issues/302) Improvement: Add optional parameters to GroupsApi.getGroups    *enhancement*  

**Removed space from min_access_level attribute name (#302).**


[654cf80d04fcc73](https://github.com/gmessner/gitlab4j-api/commit/654cf80d04fcc73) Greg Messner *2019-02-20 04:06:05*


### GitHub [#305](https://github.com/gmessner/gitlab4j-api/issues/305) A bug in projects API while getting all projects using getProjects()  

**Added support for api_kaminari_count_with_limit (#305).**


[68e98e6bb02006f](https://github.com/gmessner/gitlab4j-api/commit/68e98e6bb02006f) Greg Messner *2019-03-03 21:17:56*


### GitHub [#308](https://github.com/gmessner/gitlab4j-api/issues/308) position[position_type] does not have a valid value  

**Using lower case for Position #308 (#309)**


[26437a492cdf536](https://github.com/gmessner/gitlab4j-api/commit/26437a492cdf536) Tomas Bjerre *2019-02-20 17:39:24*


### GitHub [#309](https://github.com/gmessner/gitlab4j-api/pull/309) Using lower case for Position  

**Using lower case for Position #308 (#309)**


[26437a492cdf536](https://github.com/gmessner/gitlab4j-api/commit/26437a492cdf536) Tomas Bjerre *2019-02-20 17:39:24*


### GitHub [#312](https://github.com/gmessner/gitlab4j-api/issues/312) Add Delete merged branches api    *enhancement*  

**Fixed javadoc issue (#312).**


[b0f40f9ff0c08d8](https://github.com/gmessner/gitlab4j-api/commit/b0f40f9ff0c08d8) Greg Messner *2019-03-03 21:22:29*

**Added deleteMergedBranches() (#312).**


[251de6b868299b2](https://github.com/gmessner/gitlab4j-api/commit/251de6b868299b2) Greg Messner *2019-02-25 08:38:30*


### GitHub [#313](https://github.com/gmessner/gitlab4j-api/issues/313) Improvement: Add method to delete pipeline    *enhancement*  

**Added deletePipeline() (#313).**


[34a723b9c55a45c](https://github.com/gmessner/gitlab4j-api/commit/34a723b9c55a45c) Greg Messner *2019-02-25 08:31:29*


### GitHub [#314](https://github.com/gmessner/gitlab4j-api/issues/314) Improvement: Add method setUserAvatar to UserApi    *enhancement*  

**Cleaned up imports (#314).**


[5dba51af16f9543](https://github.com/gmessner/gitlab4j-api/commit/5dba51af16f9543) Greg Messner *2019-03-03 21:11:58*

**Added method to upload user avatar (#314).**


[a28e1d88c423084](https://github.com/gmessner/gitlab4j-api/commit/a28e1d88c423084) Greg Messner *2019-03-03 17:47:32*


### GitHub [#315](https://github.com/gmessner/gitlab4j-api/issues/315) Disable runner not correctly implemented    *bug*  

**Fixed disableRunner() (#315).**


[4dabe7113c12219](https://github.com/gmessner/gitlab4j-api/commit/4dabe7113c12219) Greg Messner *2019-03-03 17:53:47*


### No issue

**Now tests Diff using an array.**


[631db18c50a22dd](https://github.com/gmessner/gitlab4j-api/commit/631db18c50a22dd) Greg Messner *2019-03-03 21:10:52*

**Bumped version to 4.9.18 in preparation for release.**


[aeaee88894d6b57](https://github.com/gmessner/gitlab4j-api/commit/aeaee88894d6b57) Greg Messner *2019-03-03 17:49:13*

**Generated latest changelog.**


[0ae30f9e2a7ef1e](https://github.com/gmessner/gitlab4j-api/commit/0ae30f9e2a7ef1e) Greg Messner *2019-02-19 18:28:17*

**Updated Stream support section.**


[a5582bd653dd34e](https://github.com/gmessner/gitlab4j-api/commit/a5582bd653dd34e) Greg Messner *2019-02-19 17:37:48*


## gitlab4j-api-4.9.17
### GitHub [#291](https://github.com/gmessner/gitlab4j-api/issues/291) Create discussions    *enhancement*  

**Adding diff_refs in MergeRequest #291 (#300)**


[00496bf62860f85](https://github.com/gmessner/gitlab4j-api/commit/00496bf62860f85) Tomas Bjerre *2019-02-13 17:46:18*


### GitHub [#300](https://github.com/gmessner/gitlab4j-api/pull/300) Adding diff_refs in MergeRequest #291  

**Adding diff_refs in MergeRequest #291 (#300)**


[00496bf62860f85](https://github.com/gmessner/gitlab4j-api/commit/00496bf62860f85) Tomas Bjerre *2019-02-13 17:46:18*


### GitHub [#301](https://github.com/gmessner/gitlab4j-api/pull/301) Adding changelog  

**Adding Git Changelog Maven Plugin (#301)**


[57f297250465fe5](https://github.com/gmessner/gitlab4j-api/commit/57f297250465fe5) Tomas Bjerre *2019-02-13 17:47:28*


### GitHub [#302](https://github.com/gmessner/gitlab4j-api/issues/302) Improvement: Add optional parameters to GroupsApi.getGroups    *enhancement*  

**Added support to get groups with filter (#302).**


[e95af61df0027d2](https://github.com/gmessner/gitlab4j-api/commit/e95af61df0027d2) Greg Messner *2019-02-19 06:48:27*

**Initial commit (#302).**


[5a7f99c595a1c3a](https://github.com/gmessner/gitlab4j-api/commit/5a7f99c595a1c3a) Greg Messner *2019-02-19 06:47:58*


### GitHub [#304](https://github.com/gmessner/gitlab4j-api/pull/304) Added Group Milestones API  

**Added Group Milestones API (#304)**

 * Added missing start_date Milestone attribute
 * Added Group Milestones API

[f24acfaed77308a](https://github.com/gmessner/gitlab4j-api/commit/f24acfaed77308a) Pierre Smeyers *2019-02-18 16:22:51*


### No issue

**Restructured plugins.**


[5e435acd9a94f59](https://github.com/gmessner/gitlab4j-api/commit/5e435acd9a94f59) Greg Messner *2019-02-19 16:06:58*

**Removed phase from git-changelog-maven-plugin.**


[e706de0d54a855f](https://github.com/gmessner/gitlab4j-api/commit/e706de0d54a855f) Greg Messner *2019-02-19 15:24:19*

**Fixed compile warnings.**


[ad4b0f4d2589158](https://github.com/gmessner/gitlab4j-api/commit/ad4b0f4d2589158) Greg Messner *2019-02-18 17:29:22*

**Fixed eclipse issue with changelog plugin.**


[616ba42be9b864a](https://github.com/gmessner/gitlab4j-api/commit/616ba42be9b864a) Greg Messner *2019-02-18 17:28:49*

**Bumped version.**


[48490b0d62ac04f](https://github.com/gmessner/gitlab4j-api/commit/48490b0d62ac04f) Greg Messner *2019-02-18 17:28:25*

**Removed unused imports.**


[c1841fcb4ffa644](https://github.com/gmessner/gitlab4j-api/commit/c1841fcb4ffa644) Greg Messner *2019-02-18 17:28:10*


## gitlab4j-api-4.9.16
### GitHub [#295](https://github.com/gmessner/gitlab4j-api/issues/295) Stream limiting doesn&#39;t work    *enhancement*  

**Java 8 Stream Documentation improvements (#298)**

 * Added test for lazy Stream evaluation (#295).
 * Cleaned up Stream support documentaion (#295).

[9f969666909599f](https://github.com/gmessner/gitlab4j-api/commit/9f969666909599f) Greg Messner *2019-02-11 15:55:27*

**lazy streams with pager spliterator (#295) (#296)**


[0085d120b4140b4](https://github.com/gmessner/gitlab4j-api/commit/0085d120b4140b4) Mariusz Smykuła *2019-02-10 02:29:26*


### GitHub [#296](https://github.com/gmessner/gitlab4j-api/pull/296) stream improvment - page splitter  

**lazy streams with pager spliterator (#295) (#296)**


[0085d120b4140b4](https://github.com/gmessner/gitlab4j-api/commit/0085d120b4140b4) Mariusz Smykuła *2019-02-10 02:29:26*


### GitHub [#297](https://github.com/gmessner/gitlab4j-api/pull/297) Spliterator improvments  

**Spliterator improvments (#297)**

 * fix for NPE on empty source
 * add NPE when action is missing (required by contract)
 * declare characteristics

[8b782fab199dabe](https://github.com/gmessner/gitlab4j-api/commit/8b782fab199dabe) Mariusz Smykuła *2019-02-10 19:58:07*


### GitHub [#298](https://github.com/gmessner/gitlab4j-api/pull/298) Java 8 Stream Documentation improvements  

**Java 8 Stream Documentation improvements (#298)**

 * Added test for lazy Stream evaluation (#295).
 * Cleaned up Stream support documentaion (#295).

[9f969666909599f](https://github.com/gmessner/gitlab4j-api/commit/9f969666909599f) Greg Messner *2019-02-11 15:55:27*


### GitHub [#299](https://github.com/gmessner/gitlab4j-api/pull/299) verify that PagerSplitter can collect data page by page  

**Verify that PagerSplitter can collect data page by page (#299)**


[ce32db4cc098ce6](https://github.com/gmessner/gitlab4j-api/commit/ce32db4cc098ce6) Mariusz Smykuła *2019-02-11 15:40:21*


### No issue

**Cleaned up formatting.**


[509d8580800d3b8](https://github.com/gmessner/gitlab4j-api/commit/509d8580800d3b8) Greg Messner *2019-02-07 22:57:23*


## gitlab4j-api-4.9.15
### GitHub [#291](https://github.com/gmessner/gitlab4j-api/issues/291) Create discussions    *enhancement*  

**Added support for creating and resolving merge request discussions (#291).**


[d0a7d1b51aa0491](https://github.com/gmessner/gitlab4j-api/commit/d0a7d1b51aa0491) Greg Messner *2019-02-07 22:31:47*

**Initial commit (#291).**


[0e8f58f2146ccf5](https://github.com/gmessner/gitlab4j-api/commit/0e8f58f2146ccf5) Greg Messner *2019-02-07 22:31:00*


### GitHub [#292](https://github.com/gmessner/gitlab4j-api/issues/292) Name should not be required to update a project.    *bug*  

**Fixed updateProject() (#292).**


[31cd97f15ca1b32](https://github.com/gmessner/gitlab4j-api/commit/31cd97f15ca1b32) Greg Messner *2019-01-31 17:21:21*


### GitHub [#293](https://github.com/gmessner/gitlab4j-api/pull/293) chmod commit action  

**chmod commit action (#293)**

 * Added support for the chmod commit action.

[e16c596478ac9ff](https://github.com/gmessner/gitlab4j-api/commit/e16c596478ac9ff) Michal Augustýn *2019-02-07 21:42:33*


### No issue

**Bumped version to 4.9.15**


[6e608d10c7b224b](https://github.com/gmessner/gitlab4j-api/commit/6e608d10c7b224b) Greg Messner *2019-02-07 22:30:06*

**Cleaned up imports.**


[3988b28a726231a](https://github.com/gmessner/gitlab4j-api/commit/3988b28a726231a) Greg Messner *2019-02-07 22:28:15*


## gitlab4j-api-4.9.14
### GitHub [#290](https://github.com/gmessner/gitlab4j-api/issues/290) Wikis attachements    *enhancement*  

**Added support Wikis API attachemnt upload (#290).**


[71e3cce3c80c7ee](https://github.com/gmessner/gitlab4j-api/commit/71e3cce3c80c7ee) Greg Messner *2019-01-21 02:57:48*

**Added WikisApi to list of APIs (#290).**


[28ac8d01c77c2f2](https://github.com/gmessner/gitlab4j-api/commit/28ac8d01c77c2f2) Greg Messner *2019-01-21 02:56:09*

**Initial commit (#290).**


[576cffa335fb410](https://github.com/gmessner/gitlab4j-api/commit/576cffa335fb410) Greg Messner *2019-01-21 02:55:02*


### No issue

**Fixed issue with issue filter test.**


[fc54bb3a1b51d83](https://github.com/gmessner/gitlab4j-api/commit/fc54bb3a1b51d83) Greg Messner *2019-01-21 03:39:01*

**Updated jersey and jaxb versions.**


[e33eb8839d7626a](https://github.com/gmessner/gitlab4j-api/commit/e33eb8839d7626a) Greg Messner *2019-01-21 02:57:01*


## gitlab4j-api-4.9.12
### GitHub [#284](https://github.com/gmessner/gitlab4j-api/issues/284) Project API add avatar    *enhancement*  

**Fixed issue when uploading files when a proxy is being used (#284).**


[31a188aa5311ac7](https://github.com/gmessner/gitlab4j-api/commit/31a188aa5311ac7) Greg Messner *2019-01-10 07:51:06*


### GitHub [#288](https://github.com/gmessner/gitlab4j-api/issues/288) Support API Merge Base    *enhancement*  

**Added support for getting the merge base (#288).**


[ec05c5951bad654](https://github.com/gmessner/gitlab4j-api/commit/ec05c5951bad654) Greg Messner *2019-01-10 07:50:10*


### No issue

**Bumped version to 4.9.12**


[1d11da7cefbd7c8](https://github.com/gmessner/gitlab4j-api/commit/1d11da7cefbd7c8) Greg Messner *2019-01-10 07:51:44*


## gitlab4j-api-4.9.11
### GitHub [#286](https://github.com/gmessner/gitlab4j-api/issues/286) Missing &#39;X-Per-Page&#39; header from server  

**Added handling of results from GitLab API that are missing headers (#286).**


[ea9daebbeb6ed62](https://github.com/gmessner/gitlab4j-api/commit/ea9daebbeb6ed62) Greg Messner *2018-12-29 20:26:19*

**Initial commit (#286).**


[86732490fa54869](https://github.com/gmessner/gitlab4j-api/commit/86732490fa54869) Greg Messner *2018-12-29 20:25:33*


### No issue

**Bumped version to 4.9.11**


[28b39e3ac9ac10e](https://github.com/gmessner/gitlab4j-api/commit/28b39e3ac9ac10e) Greg Messner *2018-12-29 20:26:59*

**Added overloaded creatTag() method.**


[5949bc05c817ba3](https://github.com/gmessner/gitlab4j-api/commit/5949bc05c817ba3) Greg Messner *2018-12-29 20:24:48*

**Removed deprecated methods that are no loinger supported by GitLab.**


[ab292a6f1e3b760](https://github.com/gmessner/gitlab4j-api/commit/ab292a6f1e3b760) Greg Messner *2018-12-29 20:23:53*

**Corrected Javadoc for updateIssue().**


[319042a6acf306e](https://github.com/gmessner/gitlab4j-api/commit/319042a6acf306e) Greg Messner *2018-12-22 16:42:58*

**Bumped version to 4.9.10**


[34615119f51e963](https://github.com/gmessner/gitlab4j-api/commit/34615119f51e963) Greg Messner *2018-12-21 20:24:38*


## gitlab4j-api-4.9.10
### GitHub [#284](https://github.com/gmessner/gitlab4j-api/issues/284) Project API add avatar    *enhancement*  

**Simplified uploading avatar for project (#284).**


[156d828f8ccc9ba](https://github.com/gmessner/gitlab4j-api/commit/156d828f8ccc9ba) Greg Messner *2018-12-21 20:04:10*


### GitHub [#285](https://github.com/gmessner/gitlab4j-api/issues/285) IssuesApi.updateIssue requires title, an optional parameter    *bug*  

**issueIid is now used for all methods and made title optional for updateIssue() (#285).**


[6eb8bd73309ad43](https://github.com/gmessner/gitlab4j-api/commit/6eb8bd73309ad43) Greg Messner *2018-12-21 17:06:53*


### No issue

**Fixed Javadoc warnings.**


[2541e79150e4e2f](https://github.com/gmessner/gitlab4j-api/commit/2541e79150e4e2f) Greg Messner *2018-12-21 20:09:00*

**Fixed typo.**


[e0a866b85a9cc6a](https://github.com/gmessner/gitlab4j-api/commit/e0a866b85a9cc6a) Greg Messner *2018-12-19 18:45:35*

**Cleaned up Javadoc around GitLab endpoints.**


[929c74da89bfd0f](https://github.com/gmessner/gitlab4j-api/commit/929c74da89bfd0f) Greg Messner *2018-12-19 18:43:37*


## gitlab4j-api-4.9.9
### No issue

**Added getXxxxxStream() methods that return Java 8 Streams.**


[be5072a89963084](https://github.com/gmessner/gitlab4j-api/commit/be5072a89963084) Greg Messner *2018-12-17 19:26:47*

**Bumped version to 4.9.9**


[b38252fdf05032c](https://github.com/gmessner/gitlab4j-api/commit/b38252fdf05032c) Greg Messner *2018-12-17 19:26:10*


## gitlab4j-api-4.9.8
### No issue

**Added getXxxxxStream() methods that return Java 8 Streams.**


[f140bc206a9a3a7](https://github.com/gmessner/gitlab4j-api/commit/f140bc206a9a3a7) Greg Messner *2018-12-17 05:33:16*

**Bumped version to 4.9.8**


[87991488b3deec1](https://github.com/gmessner/gitlab4j-api/commit/87991488b3deec1) Greg Messner *2018-12-17 04:50:30*

**Modified to use compareJson() for comparing the items in the stream.**


[3cf79186d9ffd62](https://github.com/gmessner/gitlab4j-api/commit/3cf79186d9ffd62) Greg Messner *2018-12-17 02:48:01*

**Added compareJson() for comparing to objects of the same type.**


[0a1d1eda38ee409](https://github.com/gmessner/gitlab4j-api/commit/0a1d1eda38ee409) Greg Messner *2018-12-17 02:47:25*

**Removed unused import.**


[9997604a42c316c](https://github.com/gmessner/gitlab4j-api/commit/9997604a42c316c) Greg Messner *2018-12-17 02:35:49*

**Added methods to return the JSON for both the item and list.**


[acb7de3eb6afeda](https://github.com/gmessner/gitlab4j-api/commit/acb7de3eb6afeda) Greg Messner *2018-12-17 02:05:39*

**Added parallel Stream example.**


[529d54a6c367fd3](https://github.com/gmessner/gitlab4j-api/commit/529d54a6c367fd3) Greg Messner *2018-12-16 21:56:35*

**Changed the way sources of arrays are unmarshalled.**


[f94a51d7e590159](https://github.com/gmessner/gitlab4j-api/commit/f94a51d7e590159) Greg Messner *2018-12-16 21:50:44*

**Initial commit of Java 8 Stream tests.**


[adaccdc567e2e9d](https://github.com/gmessner/gitlab4j-api/commit/adaccdc567e2e9d) Greg Messner *2018-12-16 21:49:50*

**Added info on the getXxxxxStream() methods.**


[962525c2ebd3ba5](https://github.com/gmessner/gitlab4j-api/commit/962525c2ebd3ba5) Greg Messner *2018-12-15 21:53:58*

**Removed unused import.**


[51048544ed18b5c](https://github.com/gmessner/gitlab4j-api/commit/51048544ed18b5c) Greg Messner *2018-12-15 21:32:00*


## gitlab4j-api-4.9.7
### GitHub [#284](https://github.com/gmessner/gitlab4j-api/issues/284) Project API add avatar    *enhancement*  

**Added putUpload() methods (#284).**


[0e14ac6733a739b](https://github.com/gmessner/gitlab4j-api/commit/0e14ac6733a739b) Greg Messner *2018-12-15 19:27:33*

**Initial commit to support setting project avatar (#284).**


[a39ae6d053d0a43](https://github.com/gmessner/gitlab4j-api/commit/a39ae6d053d0a43) Greg Messner *2018-12-15 19:24:25*

**Added setProjectAvatar() (#284).**


[8119a0eda593d54](https://github.com/gmessner/gitlab4j-api/commit/8119a0eda593d54) Greg Messner *2018-12-15 19:23:15*


### No issue

**Added getXxxxx() methods that return Java 8 Streams.**


[4123ef02bdce2b0](https://github.com/gmessner/gitlab4j-api/commit/4123ef02bdce2b0) Greg Messner *2018-12-15 21:19:31*

**Bumped version to 4.9.7**


[00b4a682182e4af](https://github.com/gmessner/gitlab4j-api/commit/00b4a682182e4af) Greg Messner *2018-12-15 21:19:10*

**Added info for the DiscussionsApi.**


[4a7bb8230ee45d9](https://github.com/gmessner/gitlab4j-api/commit/4a7bb8230ee45d9) Greg Messner *2018-12-15 04:00:59*

**Fixed typo.**


[6880708eaa52757](https://github.com/gmessner/gitlab4j-api/commit/6880708eaa52757) Greg Messner *2018-12-15 03:52:49*


## gitlab4j-api-4.9.6
### GitHub [#279](https://github.com/gmessner/gitlab4j-api/pull/279) Add Discussions model and api  

**Added test for getting all discussions by list (#279).**


[d0d2cf0391a3c19](https://github.com/gmessner/gitlab4j-api/commit/d0d2cf0391a3c19) Greg Messner *2018-12-15 00:14:18*

**Changed the type for notableType to String (#279).**


[30dc929010c74b9](https://github.com/gmessner/gitlab4j-api/commit/30dc929010c74b9) Greg Messner *2018-12-15 00:03:04*

**Initial commit (#279).**


[7805e1546799924](https://github.com/gmessner/gitlab4j-api/commit/7805e1546799924) Greg Messner *2018-12-14 23:56:44*


### No issue

**Bumped version to 4.9.6**


[6614e3014136bd9](https://github.com/gmessner/gitlab4j-api/commit/6614e3014136bd9) Greg Messner *2018-12-15 00:19:26*

**Modified to use JsonUtils.**


[d53ad6370a1585b](https://github.com/gmessner/gitlab4j-api/commit/d53ad6370a1585b) Greg Messner *2018-12-15 00:02:17*

**Mods to support the FakeResponse class for mocking tests.**


[d542f3356d8114e](https://github.com/gmessner/gitlab4j-api/commit/d542f3356d8114e) Greg Messner *2018-12-15 00:00:01*

**Modified how the name is calculated for non camel cased enums.**


[c7d05708ded5de3](https://github.com/gmessner/gitlab4j-api/commit/c7d05708ded5de3) Greg Messner *2018-12-14 23:58:42*

**Initial commit.**


[fdbba9862fd281d](https://github.com/gmessner/gitlab4j-api/commit/fdbba9862fd281d) Greg Messner *2018-12-14 23:55:43*


## gitlab4j-api-4.9.5
### GitHub [#279](https://github.com/gmessner/gitlab4j-api/pull/279) Add Discussions model and api  

**Added getDiscussionsApi() (#279).**


[d1f61ecf29a05c1](https://github.com/gmessner/gitlab4j-api/commit/d1f61ecf29a05c1) Greg Messner *2018-12-14 04:28:10*


### No issue

**Bumped version to 4.9.5**


[f96acee627074b7](https://github.com/gmessner/gitlab4j-api/commit/f96acee627074b7) Greg Messner *2018-12-14 04:28:56*

**Added readResource() method for loading files needed for mocking.**


[87d8dcfc2d11c98](https://github.com/gmessner/gitlab4j-api/commit/87d8dcfc2d11c98) Greg Messner *2018-12-14 04:27:45*

**Removed the deprecated getAllProject() methods.**


[a301b25e46a04b9](https://github.com/gmessner/gitlab4j-api/commit/a301b25e46a04b9) Greg Messner *2018-12-13 20:27:31*


## gitlab4j-api-4.9.4
### GitHub [#283](https://github.com/gmessner/gitlab4j-api/issues/283) getOptionalProject with option statistics    *enhancement*  

**Added getOptionalProject() with statistics option and Stream methods for all gets that return multiple items (#283).**


[df896ea689790ea](https://github.com/gmessner/gitlab4j-api/commit/df896ea689790ea) Greg Messner *2018-12-13 19:53:13*


### No issue

**Bumped version to 4.9.4**


[b15b906b2a1e2ba](https://github.com/gmessner/gitlab4j-api/commit/b15b906b2a1e2ba) Greg Messner *2018-12-13 19:50:50*


## gitlab4j-api-4.9.3
### GitHub [#279](https://github.com/gmessner/gitlab4j-api/pull/279) Add Discussions model and api  

**Mods to support and test Discussions API (#279).**


[d9c722ae2472287](https://github.com/gmessner/gitlab4j-api/commit/d9c722ae2472287) Greg Messner *2018-12-13 07:02:22*

**Initial commit (#279).**


[4bd96b690ad755a](https://github.com/gmessner/gitlab4j-api/commit/4bd96b690ad755a) Greg Messner *2018-12-13 07:02:13*

**Add Discussions model and api (#279)**

 * Add Discussions model and API calls.

[a852499eb469679](https://github.com/gmessner/gitlab4j-api/commit/a852499eb469679) Filippo Buletto *2018-12-12 20:31:38*


### GitHub [#282](https://github.com/gmessner/gitlab4j-api/issues/282) RepositoryFileApi.getRawFile throws GitLabApiException redirected  

**Added support for v3 getRawFile() (#282).**


[fff854e321ea365](https://github.com/gmessner/gitlab4j-api/commit/fff854e321ea365) Greg Messner *2018-12-12 20:47:59*


### No issue

**Mods for deprecated methods.**


[2b91ecd4f4204c0](https://github.com/gmessner/gitlab4j-api/commit/2b91ecd4f4204c0) Greg Messner *2018-12-13 06:58:02*

**Fixed javadoc issues.**


[eee4ed1270089f4](https://github.com/gmessner/gitlab4j-api/commit/eee4ed1270089f4) Greg Messner *2018-12-13 06:56:56*

**Bumped version to 4.9.3**


[257a06ef2f9126f](https://github.com/gmessner/gitlab4j-api/commit/257a06ef2f9126f) Greg Messner *2018-12-12 20:47:13*


## gitlab4j-api-4.9.2
### GitHub [#281](https://github.com/gmessner/gitlab4j-api/issues/281) Add getOptionalBranch to repositoryApi    *enhancement*  

**Added getOptionalBranch() (#281).**


[6675a0afaa5cbd0](https://github.com/gmessner/gitlab4j-api/commit/6675a0afaa5cbd0) Greg Messner *2018-12-12 00:03:39*


### No issue

**Added Java 8 stream support.**


[bf0da6850c3b352](https://github.com/gmessner/gitlab4j-api/commit/bf0da6850c3b352) Greg Messner *2018-12-12 00:04:16*

**Added javadoc for packages_enabled.**


[a1c36764592ec0b](https://github.com/gmessner/gitlab4j-api/commit/a1c36764592ec0b) Greg Messner *2018-12-04 18:39:00*


## gitlab4j-api-4.9.1
### GitHub [#278](https://github.com/gmessner/gitlab4j-api/issues/278) &quot;Packages&quot; activation    *enhancement*  

**Added support for packages_enabled (#278).**


[fc4ed45de72ce51](https://github.com/gmessner/gitlab4j-api/commit/fc4ed45de72ce51) Greg Messner *2018-12-04 18:24:59*


### No issue

**Bumped version to 4.9.1 in preparation for release.**


[82ac043aa23ad70](https://github.com/gmessner/gitlab4j-api/commit/82ac043aa23ad70) Greg Messner *2018-12-04 18:24:27*


## gitlab4j-api-4.9.0
### GitHub [#271](https://github.com/gmessner/gitlab4j-api/issues/271) Question: Missing equals() and hashCode() implementations    *enhancement*  

**Added toString() implementation to model classes (#271)**


[b9bf3e927cc9b7f](https://github.com/gmessner/gitlab4j-api/commit/b9bf3e927cc9b7f) Greg Messner *2018-11-28 23:28:55*


### No issue

**Bumber version to 4.9.0 in prepartion for release.**


[b315de953740b36](https://github.com/gmessner/gitlab4j-api/commit/b315de953740b36) Greg Messner *2018-11-28 23:34:51*


## gitlab4j-api-4.8.58
### GitHub [#273](https://github.com/gmessner/gitlab4j-api/pull/273) Add withXxxxx() methods to AbstractUser  

**Add withXxxxx() methods to AbstractUser (#273)**


[cab7f05453f823f](https://github.com/gmessner/gitlab4j-api/commit/cab7f05453f823f) mdeknowis *2018-11-28 16:42:55*


### GitHub [#274](https://github.com/gmessner/gitlab4j-api/issues/274) Commit Status API is missing    *enhancement*  

**Mods to support addCommitStatus() (#274).**


[437b75a9bc8a7c8](https://github.com/gmessner/gitlab4j-api/commit/437b75a9bc8a7c8) Greg Messner *2018-11-28 20:43:29*


### GitHub [#276](https://github.com/gmessner/gitlab4j-api/issues/276) Ability to transfer a project    *enhancement*  

**Fixed testTransferProject() test (#276).**


[e1abdd788a1b725](https://github.com/gmessner/gitlab4j-api/commit/e1abdd788a1b725) Greg Messner *2018-11-28 22:56:32*

**Added support for transfer project (#276).**


[df7e1ec8dbd2acc](https://github.com/gmessner/gitlab4j-api/commit/df7e1ec8dbd2acc) Greg Messner *2018-11-28 22:19:03*


### No issue

**Bumped version to 4.8.59**


[ecdd58b6ea7c095](https://github.com/gmessner/gitlab4j-api/commit/ecdd58b6ea7c095) Greg Messner *2018-11-28 22:59:18*

**Fixed Javadoc warnings.**


[f93b15ddbd7459b](https://github.com/gmessner/gitlab4j-api/commit/f93b15ddbd7459b) Greg Messner *2018-11-28 17:09:32*


## gitlab4j-api-4.8.57
### GitHub [#274](https://github.com/gmessner/gitlab4j-api/issues/274) Commit Status API is missing    *enhancement*  

**Removed unused imports (#274).**


[fe8437c572f657e](https://github.com/gmessner/gitlab4j-api/commit/fe8437c572f657e) Greg Messner *2018-11-28 02:14:48*

**Updated all methods to use projectIdOrPath (#274).**


[ca6542de88e5b28](https://github.com/gmessner/gitlab4j-api/commit/ca6542de88e5b28) Greg Messner *2018-11-28 02:10:26*

**Added the ability to get commit statuses (#274).**


[b4313d09e74e519](https://github.com/gmessner/gitlab4j-api/commit/b4313d09e74e519) Greg Messner *2018-11-28 01:47:13*


### GitHub [#275](https://github.com/gmessner/gitlab4j-api/issues/275) Ability to delete an existing forked from relationship  

**Added deleteForkedFromRelationship() (#275).**


[01a610a1ed84a44](https://github.com/gmessner/gitlab4j-api/commit/01a610a1ed84a44) Greg Messner *2018-11-28 01:45:58*


### No issue

**Bumped version to 4.8.57**


[69d1cf7cca678ba](https://github.com/gmessner/gitlab4j-api/commit/69d1cf7cca678ba) Greg Messner *2018-11-28 01:52:14*


## gitlab4j-api-4.8.56
### GitHub [#270](https://github.com/gmessner/gitlab4j-api/issues/270) Slashes from path paramter in JobApi#downloadSingleArtifactsFile will result in &#39;%5C&#39; in final GET request on Windows    *bug*  

**Fixed issue using Path on Windows (#270).**


[66c2194e30a2dda](https://github.com/gmessner/gitlab4j-api/commit/66c2194e30a2dda) Greg Messner *2018-11-13 05:08:29*


### No issue

**Updated for release 4.8.56**


[336d82b626313c3](https://github.com/gmessner/gitlab4j-api/commit/336d82b626313c3) Greg Messner *2018-11-13 05:07:36*


## gitlab4j-api-4.8.55
### GitHub [#269](https://github.com/gmessner/gitlab4j-api/pull/269) Add all parameters to get project of group api    *enhancement*  

**Add all parameters to get project of group api (#269)**

 * Add all parameters listed by
 * https://docs.gitlab.com/ee/api/groups.html#list-a-groups-projects

[b78db144705f270](https://github.com/gmessner/gitlab4j-api/commit/b78db144705f270) mdeknowis *2018-11-08 03:43:51*


### No issue

**Removed usnused import.**


[5cf6b434c65e52d](https://github.com/gmessner/gitlab4j-api/commit/5cf6b434c65e52d) Greg Messner *2018-11-08 04:02:46*

**Added getProject() that returns a Pager.**


[fa84f30658ed0a1](https://github.com/gmessner/gitlab4j-api/commit/fa84f30658ed0a1) Greg Messner *2018-11-08 04:02:10*

**Updated for 4.8.55**


[714d4a87ef04334](https://github.com/gmessner/gitlab4j-api/commit/714d4a87ef04334) Greg Messner *2018-11-08 04:00:27*

**Renamed from ProjectOfGroupFilter**


[fbb9e1e6ed887f3](https://github.com/gmessner/gitlab4j-api/commit/fbb9e1e6ed887f3) Greg Messner *2018-11-08 03:57:50*


## gitlab4j-api-4.8.54
### GitHub [#268](https://github.com/gmessner/gitlab4j-api/issues/268) List projects of a certain user.  

**Added support for listing projects for a specified user (#268).**


[f6d5cff81a14578](https://github.com/gmessner/gitlab4j-api/commit/f6d5cff81a14578) Greg Messner *2018-11-02 03:35:55*

**Initial commit (#268).**


[e5c79cdb0ae8aa9](https://github.com/gmessner/gitlab4j-api/commit/e5c79cdb0ae8aa9) Greg Messner *2018-11-02 03:34:20*


### No issue

**Updated for release 4.8.54**


[4858f054504861d](https://github.com/gmessner/gitlab4j-api/commit/4858f054504861d) Greg Messner *2018-11-02 03:35:01*


## gitlab4j-api-4.8.53
### GitHub [#264](https://github.com/gmessner/gitlab4j-api/issues/264) How to find issues that are closed through merge requests    *enhancement*  

**Added getClosedByMergeRequests() methods. (#264) (#265)**


[9338e5c2cc5c6ee](https://github.com/gmessner/gitlab4j-api/commit/9338e5c2cc5c6ee) Siddharth Bulia *2018-10-30 05:08:42*


### GitHub [#265](https://github.com/gmessner/gitlab4j-api/pull/265) Added getClosedByMergeRequests() methods. (#264)  

**Added getClosedByMergeRequests() methods. (#264) (#265)**


[9338e5c2cc5c6ee](https://github.com/gmessner/gitlab4j-api/commit/9338e5c2cc5c6ee) Siddharth Bulia *2018-10-30 05:08:42*


### No issue

**Updated for release 4.8.53**


[2a868c0db24b36f](https://github.com/gmessner/gitlab4j-api/commit/2a868c0db24b36f) Greg Messner *2018-10-30 05:12:13*


## gitlab4j-api-4.8.52
### GitHub [#260](https://github.com/gmessner/gitlab4j-api/issues/260) Parser of GitLab error responses fails    *bug*  

**Now customizes the message for validation errors (#260).**


[feeb3ab9ce7f98e](https://github.com/gmessner/gitlab4j-api/commit/feeb3ab9ce7f98e) Greg Messner *2018-10-28 16:52:23*


### GitHub [#264](https://github.com/gmessner/gitlab4j-api/issues/264) How to find issues that are closed through merge requests    *enhancement*  

**Added getClosesIssues() methods (#264).**


[1dd6e1e257159ef](https://github.com/gmessner/gitlab4j-api/commit/1dd6e1e257159ef) Greg Messner *2018-10-28 16:56:46*


### No issue

**Updated for release 4.8.52**


[769168dcbad660e](https://github.com/gmessner/gitlab4j-api/commit/769168dcbad660e) Greg Messner *2018-10-28 16:55:43*


## gitlab4j-api-4.8.51
### GitHub [#260](https://github.com/gmessner/gitlab4j-api/issues/260) Parser of GitLab error responses fails    *bug*  

**Mods to support error, message, and validation errors (#260).**


[4c028c4f8040399](https://github.com/gmessner/gitlab4j-api/commit/4c028c4f8040399) Greg Messner *2018-10-28 00:17:35*

**No longer used (#260).**


[d0b0593b8da2087](https://github.com/gmessner/gitlab4j-api/commit/d0b0593b8da2087) Greg Messner *2018-10-28 00:14:47*

**Initial commit (#260).**


[6da617dcb57d8ba](https://github.com/gmessner/gitlab4j-api/commit/6da617dcb57d8ba) Greg Messner *2018-10-28 00:14:08*


### GitHub [#262](https://github.com/gmessner/gitlab4j-api/pull/262) Use correct visibility value in addGroup(Group)  

**Use correct visibility value in addGroup(Group) (#262)**


[21b7ce941e8470b](https://github.com/gmessner/gitlab4j-api/commit/21b7ce941e8470b) mdeknowis *2018-10-27 02:41:54*


### GitHub [#263](https://github.com/gmessner/gitlab4j-api/pull/263) Add support for resolve_outdated_diff_discussions in Project  

**Add support for resolve_outdated_diff_discussions in Project (#263)**

 * Add support for resolve_outdated_diff_discussions in Project
 * Add support for initialize_with_readme in Project

[333f1e56240f5db](https://github.com/gmessner/gitlab4j-api/commit/333f1e56240f5db) mdeknowis *2018-10-27 02:40:57*


### No issue

**Fixed javadoc issues.**


[68490332ffb8098](https://github.com/gmessner/gitlab4j-api/commit/68490332ffb8098) Greg Messner *2018-10-28 00:26:44*

**Updated for release 4.8.51**


[8176577fd557cff](https://github.com/gmessner/gitlab4j-api/commit/8176577fd557cff) Greg Messner *2018-10-28 00:17:22*


## gitlab4j-api-4.8.50
### GitHub [#259](https://github.com/gmessner/gitlab4j-api/pull/259) Adding ExternalWiki Service  

**Adding ExternalWiki Service (#259)**


[908f75cf23aae1c](https://github.com/gmessner/gitlab4j-api/commit/908f75cf23aae1c) Hannes De Clercq *2018-10-13 07:18:44*


### No issue

**Updated for release 4.8.50**


[6d4869fc42fe888](https://github.com/gmessner/gitlab4j-api/commit/6d4869fc42fe888) Greg Messner *2018-10-13 07:23:18*


## gitlab4j-api-4.8.49
### GitHub [#255](https://github.com/gmessner/gitlab4j-api/pull/255) feat: changing customAttributes for users  

**feat: changing customAttributes for users (#255)**


[6c9cf0f880c6e41](https://github.com/gmessner/gitlab4j-api/commit/6c9cf0f880c6e41) Ruben Vitt *2018-09-21 06:34:31*


### GitHub [#257](https://github.com/gmessner/gitlab4j-api/issues/257) Invalid URL for getRepositoryArchive  

**fix #257 archive name in url for RepositoryApi.getRepositoryArchive (#258)**


[55809a2aa455786](https://github.com/gmessner/gitlab4j-api/commit/55809a2aa455786) Michal Hlavac *2018-09-26 03:12:10*


### GitHub [#258](https://github.com/gmessner/gitlab4j-api/pull/258) fix #257 archive name in url for RepositoryApi.getRepositoryArchive  

**fix #257 archive name in url for RepositoryApi.getRepositoryArchive (#258)**


[55809a2aa455786](https://github.com/gmessner/gitlab4j-api/commit/55809a2aa455786) Michal Hlavac *2018-09-26 03:12:10*


### No issue

**Updated for release 4.8.49**


[20f718ad411ba8d](https://github.com/gmessner/gitlab4j-api/commit/20f718ad411ba8d) Greg Messner *2018-09-28 04:08:39*


## gitlab4j-api-4.8.48
### GitHub [#252](https://github.com/gmessner/gitlab4j-api/pull/252) feat-impl: custom attributes for users  

**Now only includes with_custom_attributes param if enabled (#252).**


[2bd1f51ea33c43c](https://github.com/gmessner/gitlab4j-api/commit/2bd1f51ea33c43c) Greg Messner *2018-09-19 03:14:51*

**feat-impl: custom attributes for users (#252)**


[d0c5722846b8d05](https://github.com/gmessner/gitlab4j-api/commit/d0c5722846b8d05) Ruben Vitt *2018-09-19 02:41:04*


### GitHub [#253](https://github.com/gmessner/gitlab4j-api/issues/253) ClassCast-Exception on updating JiraService without TransitionID  

**Mods to fix cast class exception related to jira_issue_transition_id (#253).**


[555181369dfc786](https://github.com/gmessner/gitlab4j-api/commit/555181369dfc786) Greg Messner *2018-09-19 02:40:20*


### No issue

**Changed jira_issue_transition_id value to an integer.**


[161e6aada09da81](https://github.com/gmessner/gitlab4j-api/commit/161e6aada09da81) Greg Messner *2018-09-19 03:13:54*

**Updated for release 4.8.48**


[beab02f78efddb8](https://github.com/gmessner/gitlab4j-api/commit/beab02f78efddb8) Greg Messner *2018-09-19 02:38:51*


## gitlab4j-api-4.8.47
### GitHub [#245](https://github.com/gmessner/gitlab4j-api/pull/245) Add note on Gradle requirements  

**Add note on Gradle requirements (#245)**

 * Add note on Gradle requirements

[78fd37178f4f21a](https://github.com/gmessner/gitlab4j-api/commit/78fd37178f4f21a) ETL *2018-09-07 14:46:49*


### GitHub [#246](https://github.com/gmessner/gitlab4j-api/pull/246) Added three getIssues() methods with all optionals in Issues Api.  

**Added three getIssues() methods with all optionals in Issues Api. (#246)**


[880b5a318337844](https://github.com/gmessner/gitlab4j-api/commit/880b5a318337844) zhengrenjie *2018-09-13 02:52:25*


### GitHub [#248](https://github.com/gmessner/gitlab4j-api/issues/248) WebHookManager check X-Gitlab-Event header not exist.  

**Changes handling of missing X-GitLab-Event header (#248).**


[466d078047eb9de](https://github.com/gmessner/gitlab4j-api/commit/466d078047eb9de) Greg Messner *2018-09-13 03:27:36*


### GitHub [#250](https://github.com/gmessner/gitlab4j-api/issues/250) Bad Request when searching for merge request by label  

**Fixed filtering by label (#250).**


[3eeb140c32712d1](https://github.com/gmessner/gitlab4j-api/commit/3eeb140c32712d1) Greg Messner *2018-09-13 03:26:27*


### GitHub [#251](https://github.com/gmessner/gitlab4j-api/issues/251) org.gitlab4j.api.GitLabApiException: Method Not Allowed  

**Fixed blockUser() and unblockUser() for V3 (#251).**


[7b6f8b322d253de](https://github.com/gmessner/gitlab4j-api/commit/7b6f8b322d253de) Greg Messner *2018-09-13 03:25:22*


### No issue

**Updated for release 4.8.47**


[18342c62e8f7ad9](https://github.com/gmessner/gitlab4j-api/commit/18342c62e8f7ad9) Greg Messner *2018-09-13 03:35:19*


## gitlab4j-api-4.8.46
### GitHub [#243](https://github.com/gmessner/gitlab4j-api/issues/243) `org.gitlab4j.api.models.Permissions` do not have setter function.  

**Added set methods (#243).**


[ffc35efe1ff6995](https://github.com/gmessner/gitlab4j-api/commit/ffc35efe1ff6995) Greg Messner *2018-09-06 04:28:56*


### GitHub [#244](https://github.com/gmessner/gitlab4j-api/pull/244) Fix Repository Archive API Format Bug.  

**Fix Repository Archive API Format Bug (#244)**


[fb29027db092821](https://github.com/gmessner/gitlab4j-api/commit/fb29027db092821) zhengrenjie *2018-09-05 13:41:57*


### No issue

**Mods to use enums for archive format.**


[ae9200f04ca391b](https://github.com/gmessner/gitlab4j-api/commit/ae9200f04ca391b) Greg Messner *2018-09-06 04:28:09*

**Updated for release 4.8.46**


[1f358da78a701d6](https://github.com/gmessner/gitlab4j-api/commit/1f358da78a701d6) Greg Messner *2018-09-06 04:27:22*

**Added downloadArtifactsFile() methods that take an ArtifactsFile instance.**


[1047d7b1babeb6c](https://github.com/gmessner/gitlab4j-api/commit/1047d7b1babeb6c) Greg Messner *2018-09-01 04:16:13*


## gitlab4j-api-4.8.45
### GitHub [#242](https://github.com/gmessner/gitlab4j-api/pull/242) feat: update group with groupApi  

**feat: update group with groupApi (#242)**

 * Update group with groupApi
 * Add group with groupApi

[bee8b86cd10c558](https://github.com/gmessner/gitlab4j-api/commit/bee8b86cd10c558) Ruben Vitt *2018-08-31 19:56:14*


### No issue

**Updated for release 4.8.45**


[8ae6a864da57770](https://github.com/gmessner/gitlab4j-api/commit/8ae6a864da57770) Greg Messner *2018-08-31 20:00:11*


## gitlab4j-api-4.8.44
### GitHub [#241](https://github.com/gmessner/gitlab4j-api/issues/241) There is no way to download the artifact archive of a failed job.    *enhancement*  

**Added missing fields (#241).**


[14a437f829b58f1](https://github.com/gmessner/gitlab4j-api/commit/14a437f829b58f1) Greg Messner *2018-08-29 03:14:28*

**Initial commit (#241).**


[a5ca728b63e8dbd](https://github.com/gmessner/gitlab4j-api/commit/a5ca728b63e8dbd) Greg Messner *2018-08-29 03:13:44*

**Added methods to download job artifacts by job ID (#241).**


[590867a04af5163](https://github.com/gmessner/gitlab4j-api/commit/590867a04af5163) Greg Messner *2018-08-29 02:17:40*

**Initial commit (#241).**


[6971e41cff8d59a](https://github.com/gmessner/gitlab4j-api/commit/6971e41cff8d59a) Greg Messner *2018-08-29 02:17:31*


### No issue

**Updated for release 4.8.44**


[37eb7bf0c2c211b](https://github.com/gmessner/gitlab4j-api/commit/37eb7bf0c2c211b) Greg Messner *2018-08-29 02:17:19*


## gitlab4j-api-4.8.43
### GitHub [#240](https://github.com/gmessner/gitlab4j-api/issues/240) RepositoryFile update content, how?  

**Fixed issue with encodeAndSetContent() (#240).**


[5e766fd18344f14](https://github.com/gmessner/gitlab4j-api/commit/5e766fd18344f14) Greg Messner *2018-08-21 18:55:16*

**Added encode/decode convenience methods for content (#240).**


[f6edda9a683ba53](https://github.com/gmessner/gitlab4j-api/commit/f6edda9a683ba53) Greg Messner *2018-08-21 18:50:26*


### No issue

**Updated for release 4.8.43**


[0398df3c92cbab6](https://github.com/gmessner/gitlab4j-api/commit/0398df3c92cbab6) Greg Messner *2018-08-21 18:50:23*

**Removed unused imports.**


[d63671310a1f52c](https://github.com/gmessner/gitlab4j-api/commit/d63671310a1f52c) Greg Messner *2018-08-19 05:27:13*


## gitlab4j-api-4.8.42
### GitHub [#234](https://github.com/gmessner/gitlab4j-api/pull/234) fix(systemhooks): add repositoryUpdateEvents as field to SystemHook m…  

**Added toJsonString to be used by toString() methods in model classes (#234).**


[3bbacbd85261179](https://github.com/gmessner/gitlab4j-api/commit/3bbacbd85261179) Greg Messner *2018-08-19 03:51:08*

**fix(systemhooks): add repositoryUpdateEvents as field to SystemHook model (#234)**


[b50c50a21749d19](https://github.com/gmessner/gitlab4j-api/commit/b50c50a21749d19) Jens Goldhammer *2018-08-19 02:06:59*


### GitHub [#235](https://github.com/gmessner/gitlab4j-api/issues/235) Builder pattern    *enhancement*  

**Added withXXX() methods (#235).**


[b3b0867a46747d1](https://github.com/gmessner/gitlab4j-api/commit/b3b0867a46747d1) Greg Messner *2018-08-19 03:48:51*


### GitHub [#236](https://github.com/gmessner/gitlab4j-api/pull/236) fix: LAST_ACTIVITY =&gt; LAST_ACTIVITY_AT  

**fix: LAST_ACTIVITY => LAST_ACTIVITY_AT (#236)**


[bd8850a8eba5447](https://github.com/gmessner/gitlab4j-api/commit/bd8850a8eba5447) khan *2018-08-16 03:17:33*


### GitHub [#237](https://github.com/gmessner/gitlab4j-api/pull/237) Some fluent-API implementations  

**Some fluent-API implementations (#237)**

 * add fluent api to event-model
 * add fluent api to commit-model
 * add fluent api to branch-model
 * add fluent api to eventData-model
 * add fluent api to protectedBranch-model
 * add fluent api to runner-model
 * add fluent api to job-model

[b1460df9c78f76f](https://github.com/gmessner/gitlab4j-api/commit/b1460df9c78f76f) Ruben Vitt *2018-08-16 03:16:41*


### GitHub [#238](https://github.com/gmessner/gitlab4j-api/issues/238) WebHookManager can not managed the WebHook request URL.    *enhancement*  

**Mods to support requestUrl and requestQuesyString properties (#238).**


[97b50e81e119dfe](https://github.com/gmessner/gitlab4j-api/commit/97b50e81e119dfe) Greg Messner *2018-08-19 05:16:16*


### No issue

**Updated for release 4.8.42**


[ce2268101e6d6ec](https://github.com/gmessner/gitlab4j-api/commit/ce2268101e6d6ec) Greg Messner *2018-08-19 05:17:43*


## gitlab4j-api-4.8.41
### GitHub [#231](https://github.com/gmessner/gitlab4j-api/pull/231) add fluent interface implementation to groupModel  

**Add fluent interface implementation to Group, Member, and Namespace models (#231)**

 * add fluid property-setter to Group model
 * add fluid property-setter to Member model
 * add fluid property-setter to Namespace model

[bb52716c95e99f0](https://github.com/gmessner/gitlab4j-api/commit/bb52716c95e99f0) Ruben Vitt *2018-08-14 01:42:34*


### GitHub [#232](https://github.com/gmessner/gitlab4j-api/issues/232) Exception handling in getOptionalMember of ProjectApi    *bug*  

**Removed throws clause fro getOptionalMember() (#232).**


[a65430811ca8244](https://github.com/gmessner/gitlab4j-api/commit/a65430811ca8244) Greg Messner *2018-08-14 02:57:03*


### No issue

**Updated fro release 4.8.41**


[f9155e0562b39c6](https://github.com/gmessner/gitlab4j-api/commit/f9155e0562b39c6) Greg Messner *2018-08-14 01:52:28*


## gitlab4j-api-4.8.40
### GitHub [#230](https://github.com/gmessner/gitlab4j-api/issues/230) Create pipeline doesn&#39;t work as expected    *bug*  

**Fixed createPipeline() (#230).**


[00821cea6bd7fe7](https://github.com/gmessner/gitlab4j-api/commit/00821cea6bd7fe7) Greg Messner *2018-08-12 18:53:39*


### No issue

**Udated for release 4.8.40**


[db5b6b53b8235a1](https://github.com/gmessner/gitlab4j-api/commit/db5b6b53b8235a1) Greg Messner *2018-08-12 18:53:05*


## gitlab4j-api-4.8.39
### GitHub [#228](https://github.com/gmessner/gitlab4j-api/issues/228) enable logging of http requests/responses    *enhancement*  

**Added info on enabling request/response logging (#228).**


[12f5d8670541c3b](https://github.com/gmessner/gitlab4j-api/commit/12f5d8670541c3b) Greg Messner *2018-08-07 07:33:35*

**Added support to log requests/responses when communicating with the GitLab API (#228).**


[94bac90d783733f](https://github.com/gmessner/gitlab4j-api/commit/94bac90d783733f) Greg Messner *2018-08-07 07:24:04*

**Mods to use shared Logger from GitLabApi class (#228).**


[8281b079747841f](https://github.com/gmessner/gitlab4j-api/commit/8281b079747841f) Greg Messner *2018-08-07 07:22:19*

**Initial commit (#228).**


[4eb856c7ab54179](https://github.com/gmessner/gitlab4j-api/commit/4eb856c7ab54179) Jens Goldhammer *2018-08-07 07:21:15*

**Added >com.github.stefanbirkner dependency (#228).**


[4a324953340cf91](https://github.com/gmessner/gitlab4j-api/commit/4a324953340cf91) Greg Messner *2018-08-07 07:21:05*


## gitlab4j-api-4.8.38
### GitHub [#226](https://github.com/gmessner/gitlab4j-api/pull/226) feat(runners): additions for runners api  

**feat(runners): additions for runners api (#226)**

 * feat(runners): additions for RunnersApi
 * - add register and delete runner
 * - enhance runner entity for ipAdress field
 * - introduce first runners tests

[8d917f17b903bd2](https://github.com/gmessner/gitlab4j-api/commit/8d917f17b903bd2) Jens Goldhammer *2018-08-04 22:17:51*


### No issue

**Updated for release 4.8.38**


[0253df83cc010e7](https://github.com/gmessner/gitlab4j-api/commit/0253df83cc010e7) Greg Messner *2018-08-04 23:19:34*

**Code cleanup.**


[9791988f2f3602c](https://github.com/gmessner/gitlab4j-api/commit/9791988f2f3602c) Greg Messner *2018-08-04 23:18:33*

**Code cleanup and Javadoc fixes.**


[561cef955d4230a](https://github.com/gmessner/gitlab4j-api/commit/561cef955d4230a) Greg Messner *2018-08-04 23:17:51*


## gitlab4j-api-4.8.37
### GitHub [#224](https://github.com/gmessner/gitlab4j-api/issues/224) Check if file exists in repo    *enhancement*  

**Added getOptionalFile() and getOptionalFileInfo() methods (#224).**


[47883737f6f0278](https://github.com/gmessner/gitlab4j-api/commit/47883737f6f0278) Greg Messner *2018-07-26 03:24:50*

**Moved RepositoryFileApi tests into their own test class (#224).**


[19094fcd5348ec0](https://github.com/gmessner/gitlab4j-api/commit/19094fcd5348ec0) Greg Messner *2018-07-26 03:24:12*

**Initial commit (#224).**


[09d6df533893198](https://github.com/gmessner/gitlab4j-api/commit/09d6df533893198) Greg Messner *2018-07-26 03:22:25*


### No issue

**Updated for release 4.8.37**


[576606eb5e1b670](https://github.com/gmessner/gitlab4j-api/commit/576606eb5e1b670) Greg Messner *2018-07-26 03:25:53*


## gitlab4j-api-4.8.36
### GitHub [#224](https://github.com/gmessner/gitlab4j-api/issues/224) Check if file exists in repo    *enhancement*  

**Added support to fetch file information without fetching the file content (#224).**


[17d2703fde664cf](https://github.com/gmessner/gitlab4j-api/commit/17d2703fde664cf) Greg Messner *2018-07-25 05:33:16*


### No issue

**Updated for release 4.8.36**


[fbfa873e33472df](https://github.com/gmessner/gitlab4j-api/commit/fbfa873e33472df) Greg Messner *2018-07-25 05:32:04*


## gitlab4j-api-4.8.35
### No issue

**Defined all methods as default to simplify implementations.**


[092bcaf75d5cfdd](https://github.com/gmessner/gitlab4j-api/commit/092bcaf75d5cfdd) Greg Messner *2018-07-20 18:12:21*

**Updated for release 4.8.35**


[6065baadf7aff20](https://github.com/gmessner/gitlab4j-api/commit/6065baadf7aff20) Greg Messner *2018-07-20 18:11:40*


## gitlab4j-api-4.8.34
### GitHub [#212](https://github.com/gmessner/gitlab4j-api/issues/212) Filtering of Merge Request by date wise    *non-issue*  

**Added ability to filter merge requests by project and IID (#212).**


[fdc2644bec6b051](https://github.com/gmessner/gitlab4j-api/commit/fdc2644bec6b051) Greg Messner *2018-07-19 15:43:51*


### No issue

**Updated for release 4.8.34**


[d04643797b55545](https://github.com/gmessner/gitlab4j-api/commit/d04643797b55545) Greg Messner *2018-07-19 15:09:13*


## gitlab4j-api-4.8.33
### No issue

**Update for release 4.8.33**


[e242bac74379157](https://github.com/gmessner/gitlab4j-api/commit/e242bac74379157) Greg Messner *2018-07-19 00:27:08*

**Updated dependency versions to latest available.**


[48cc0282352b8a6](https://github.com/gmessner/gitlab4j-api/commit/48cc0282352b8a6) Greg Messner *2018-07-14 23:07:34*

**Now uses API v4.**


[f85855036d869d8](https://github.com/gmessner/gitlab4j-api/commit/f85855036d869d8) Greg Messner *2018-07-14 23:07:03*

**Added notice of pending removal of GitLab API v3 support.**


[1ac45c061704498](https://github.com/gmessner/gitlab4j-api/commit/1ac45c061704498) Greg Messner *2018-07-13 18:15:43*


## gitlab4j-api-4.8.32
### GitHub [#223](https://github.com/gmessner/gitlab4j-api/issues/223) ldap_group_links support  

**Fixed issue with addLdapGroupLink() (#223).**


[d26ae08181c850d](https://github.com/gmessner/gitlab4j-api/commit/d26ae08181c850d) Greg Messner *2018-07-11 16:31:30*

**Added withParam() method that takes AccessLevel as the value (#223).**


[d839d8ec037f106](https://github.com/gmessner/gitlab4j-api/commit/d839d8ec037f106) Greg Messner *2018-07-11 16:29:42*


### No issue

**Updated for release 4.8.32**


[416e03cb407998b](https://github.com/gmessner/gitlab4j-api/commit/416e03cb407998b) Greg Messner *2018-07-11 16:45:01*

**Fixed javadoc error.**


[d6e23de77d639ab](https://github.com/gmessner/gitlab4j-api/commit/d6e23de77d639ab) Greg Messner *2018-07-11 16:39:19*

**Now uses withParam() method that takes AccessLevel as the value.**


[57754393c7a3a91](https://github.com/gmessner/gitlab4j-api/commit/57754393c7a3a91) Greg Messner *2018-07-11 16:30:54*


## gitlab4j-api-4.8.31
### GitHub [#221](https://github.com/gmessner/gitlab4j-api/issues/221) updateIssueNote throws an GitLabApiException with the status text &quot;OK&quot; (status code check does not match)  

**Use status code 200 instead of 201. #221 (#222)**


[085373d06964598](https://github.com/gmessner/gitlab4j-api/commit/085373d06964598) Kevin Urbainczyk *2018-07-10 16:07:16*


### GitHub [#222](https://github.com/gmessner/gitlab4j-api/pull/222) Use status code 200 instead of 201. #221  

**Use status code 200 instead of 201. #221 (#222)**


[085373d06964598](https://github.com/gmessner/gitlab4j-api/commit/085373d06964598) Kevin Urbainczyk *2018-07-10 16:07:16*


### GitHub [#223](https://github.com/gmessner/gitlab4j-api/issues/223) ldap_group_links support  

**Added support for LDAP syncing (#223).**


[1e29cfd20fbd79f](https://github.com/gmessner/gitlab4j-api/commit/1e29cfd20fbd79f) Greg Messner *2018-07-11 04:37:15*


### No issue

**Updated for release 4.8.31**


[c08a759ea160477](https://github.com/gmessner/gitlab4j-api/commit/c08a759ea160477) Greg Messner *2018-07-11 04:37:24*

**Corrected response code on put() calls from CREATED to OK.**


[8994a65db3bfc43](https://github.com/gmessner/gitlab4j-api/commit/8994a65db3bfc43) Greg Messner *2018-07-10 18:20:30*

**Fixed deprecation warning.**


[a5073f107bcee3f](https://github.com/gmessner/gitlab4j-api/commit/a5073f107bcee3f) Greg Messner *2018-07-10 18:19:39*


## gitlab4j-api-4.8.30
### GitHub [#214](https://github.com/gmessner/gitlab4j-api/issues/214) AwardEmoji API    *enhancement*  

**Add Award Emoji API support (#214, #219)**


[558cabe2fbba789](https://github.com/gmessner/gitlab4j-api/commit/558cabe2fbba789) Greg Messner *2018-07-07 23:50:57*


### GitHub [#217](https://github.com/gmessner/gitlab4j-api/pull/217) Additional method for create of Merge Request with squash parameter  

**Additional method for create of Merge Request with squash parameter (#217)**


[53845f32c1e9ad7](https://github.com/gmessner/gitlab4j-api/commit/53845f32c1e9ad7) eutkin *2018-07-07 19:31:47*


### GitHub [#218](https://github.com/gmessner/gitlab4j-api/pull/218) Add Epic and Epic Issues API support    *enhancement*  

**Fixed repsonse codes (#218).**


[b89b7f024326e04](https://github.com/gmessner/gitlab4j-api/commit/b89b7f024326e04) Greg Messner *2018-07-07 23:31:10*

**Add Epic and Epic Issues API support (#218)**


[4b72785355676eb](https://github.com/gmessner/gitlab4j-api/commit/4b72785355676eb) Greg Messner *2018-07-07 19:30:17*


### GitHub [#219](https://github.com/gmessner/gitlab4j-api/pull/219) Add Award Emoji API support  

**Add Award Emoji API support (#214, #219)**


[558cabe2fbba789](https://github.com/gmessner/gitlab4j-api/commit/558cabe2fbba789) Greg Messner *2018-07-07 23:50:57*


### No issue

**Updated mockito version.**


[2263d7618096a7d](https://github.com/gmessner/gitlab4j-api/commit/2263d7618096a7d) Greg Messner *2018-07-05 23:35:07*


## gitlab4j-api-4.8.29
### No issue

**Updated for release 4.8.29**


[53f637fc444c6e7](https://github.com/gmessner/gitlab4j-api/commit/53f637fc444c6e7) Greg Messner *2018-07-05 22:32:58*

**Added getGroupIdOrPath().**


[74a144b86afdc16](https://github.com/gmessner/gitlab4j-api/commit/74a144b86afdc16) Greg Messner *2018-07-05 22:32:03*

**Added deprecated tags to all calls replaced by the TagsApi.**


[4b18991bfde3ec8](https://github.com/gmessner/gitlab4j-api/commit/4b18991bfde3ec8) Greg Messner *2018-07-05 20:47:47*

**Added support for TagsApi.**


[7e7c39140111084](https://github.com/gmessner/gitlab4j-api/commit/7e7c39140111084) Greg Messner *2018-07-05 20:46:17*

**Initial commit.**


[949e4eb8e860683](https://github.com/gmessner/gitlab4j-api/commit/949e4eb8e860683) Greg Messner *2018-07-05 20:45:50*


## gitlab4j-api-4.8.28
### GitHub [#207](https://github.com/gmessner/gitlab4j-api/pull/207) adding repository and project to MergeRequestEvent  

**Add repository and project to the MergeRequestEvent (#207)**


[d53f2d32bfd3ee5](https://github.com/gmessner/gitlab4j-api/commit/d53f2d32bfd3ee5) nodoze *2018-07-03 16:47:13*


### GitHub [#209](https://github.com/gmessner/gitlab4j-api/pull/209) Add download a single artifact file endpoint  

**Add download a single artifact file endpoint (#209)**

 * Download a single artifact file from within the job&#39;s artifacts archive.
 * Only a single file is going to be extracted from the archive and streamed to a client.

[cf7a81f1be6b67a](https://github.com/gmessner/gitlab4j-api/commit/cf7a81f1be6b67a) M.P. Korstanje *2018-07-03 16:49:17*


### GitHub [#211](https://github.com/gmessner/gitlab4j-api/pull/211) Add Release Api  

**Added createRelease(), updateRelease() (#211)**


[e2ee6feff9f2404](https://github.com/gmessner/gitlab4j-api/commit/e2ee6feff9f2404) eutkin *2018-07-05 07:21:16*


### No issue

**Fixed testCommitRefs().**


[530d75195a15084](https://github.com/gmessner/gitlab4j-api/commit/530d75195a15084) Greg Messner *2018-07-05 07:36:46*

**Fixed RefType issue.**


[24fbb5bc1f01ab0](https://github.com/gmessner/gitlab4j-api/commit/24fbb5bc1f01ab0) Greg Messner *2018-07-05 07:28:32*

**Updated for release 4.8.28**


[75698879abdebfd](https://github.com/gmessner/gitlab4j-api/commit/75698879abdebfd) Greg Messner *2018-07-05 07:08:32*

**Fixed issue with unit test failure.**


[209ad5a563c6eb4](https://github.com/gmessner/gitlab4j-api/commit/209ad5a563c6eb4) Greg Messner *2018-07-05 07:05:59*

**Moved reordered properties.**


[3526b07c8371303](https://github.com/gmessner/gitlab4j-api/commit/3526b07c8371303) Greg Messner *2018-07-05 07:05:21*

**Added id property.**


[9fbcbf9d9477885](https://github.com/gmessner/gitlab4j-api/commit/9fbcbf9d9477885) Greg Messner *2018-07-05 07:03:47*

**Added File downloadSingleArtifactsFile() method.**


[f3c220007042f6b](https://github.com/gmessner/gitlab4j-api/commit/f3c220007042f6b) Greg Messner *2018-07-05 07:01:54*

**Fixed NPE.**


[065d44f65d984bf](https://github.com/gmessner/gitlab4j-api/commit/065d44f65d984bf) Greg Messner *2018-07-05 07:00:23*


## gitlab4j-api-4.8.27
### GitHub [#204](https://github.com/gmessner/gitlab4j-api/pull/204) added some apis  

**Added LicenseApi and MarkdownApi (#204)**

 * 1) Added a method to get languages used in a project.
 * 2) Added LicensesApi &amp; MarkdownApi classes and methods

[f75f0da08381a91](https://github.com/gmessner/gitlab4j-api/commit/f75f0da08381a91) zhengrenjie *2018-06-28 07:45:33*


### GitHub [#206](https://github.com/gmessner/gitlab4j-api/pull/206) Adds a new updateMergeRequest method with more attributes  

**Adds a new updateMergeRequest method with more attributes (#206)**


[f1f270c573ef852](https://github.com/gmessner/gitlab4j-api/commit/f1f270c573ef852) Adam Snyder *2018-06-28 15:20:27*


### No issue

**Added unit tests for getProjectLanguages().**


[2743a6c544f3d69](https://github.com/gmessner/gitlab4j-api/commit/2743a6c544f3d69) Greg Messner *2018-06-28 15:36:49*

**Updated for release 4.8.27**


[7da931fcdd1b414](https://github.com/gmessner/gitlab4j-api/commit/7da931fcdd1b414) Greg Messner *2018-06-28 15:36:27*


## gitlab4j-api-4.8.26
### GitHub [#202](https://github.com/gmessner/gitlab4j-api/issues/202) Getting Bad Request, possibly due to Date.toString  

**Added additional test for createIssue() (#202).**


[9c361d7b4676cb9](https://github.com/gmessner/gitlab4j-api/commit/9c361d7b4676cb9) Greg Messner *2018-06-27 05:30:20*

**Added support for Date and List form params (#202).**


[2c208a095773155](https://github.com/gmessner/gitlab4j-api/commit/2c208a095773155) Greg Messner *2018-06-27 05:29:42*


### GitHub [#203](https://github.com/gmessner/gitlab4j-api/issues/203) Gitlab delete group break  

**validate() now properly handles expected HTTP status in the 200-204 range (#203).**


[c8ccbaa7e60b47c](https://github.com/gmessner/gitlab4j-api/commit/c8ccbaa7e60b47c) Greg Messner *2018-06-27 05:29:28*


### No issue

**Updated for release 4.8.26**


[b997becd7120a49](https://github.com/gmessner/gitlab4j-api/commit/b997becd7120a49) Greg Messner *2018-06-27 05:31:17*


## gitlab4j-api-4.8.25
### GitHub [#198](https://github.com/gmessner/gitlab4j-api/issues/198) Gitlab renames Master to Maintainer  

**Deprecated MASTER and added MAINTAINER access level (#198).**


[b87da212ba1479d](https://github.com/gmessner/gitlab4j-api/commit/b87da212ba1479d) Greg Messner *2018-06-14 15:23:27*


### GitHub [#200](https://github.com/gmessner/gitlab4j-api/pull/200) Add two getProject() methods which would support to get a single project with statistics.  

**Added getProject() methods which return a single project with statistics and methods for retrieving project archives with a specified format. (#200)**


[b90c81899b7627c](https://github.com/gmessner/gitlab4j-api/commit/b90c81899b7627c) zhengrenjie *2018-06-22 03:11:57*


### No issue

**Fixed broken test caused by new getRepositoryArchive() methods.**


[51bd50f124811d7](https://github.com/gmessner/gitlab4j-api/commit/51bd50f124811d7) Greg Messner *2018-06-22 03:24:31*

**Updated for release 4.8.25**


[bbb63cb6c35de7a](https://github.com/gmessner/gitlab4j-api/commit/bbb63cb6c35de7a) Greg Messner *2018-06-22 03:15:17*

**Updated JAX-RS issue links to point to new location.**


[97e21a814262a72](https://github.com/gmessner/gitlab4j-api/commit/97e21a814262a72) Greg Messner *2018-06-19 14:59:56*


## gitlab4j-api-4.8.24
### GitHub [#197](https://github.com/gmessner/gitlab4j-api/issues/197) createFile bug  

**Now allows for empty content when creating or updating a file (#197).**


[c13236a1e797c85](https://github.com/gmessner/gitlab4j-api/commit/c13236a1e797c85) Greg Messner *2018-06-13 13:50:55*


### No issue

**Updated for release 4.8.24**


[42f5d48a2fac7b6](https://github.com/gmessner/gitlab4j-api/commit/42f5d48a2fac7b6) Greg Messner *2018-06-13 13:53:34*


## gitlab4j-api-4.8.23
### GitHub [#171](https://github.com/gmessner/gitlab4j-api/issues/171) Project API &quot;Upload a file&quot; and &quot;Push Rules&quot;    *enhancement*  

**Fixed updatePushRules() (#171).**


[db379f1243a5ff4](https://github.com/gmessner/gitlab4j-api/commit/db379f1243a5ff4) Greg Messner *2018-06-12 18:48:10*


### No issue

**Updated for release 4.8.23**


[63b91e2741ead49](https://github.com/gmessner/gitlab4j-api/commit/63b91e2741ead49) Greg Messner *2018-06-12 18:48:51*


## gitlab4j-api-4.8.22
### GitHub [#190](https://github.com/gmessner/gitlab4j-api/issues/190) when i  use getUserEvents method with ActionType or TargetType parameter  got error   

**Fixed issue with target_type when fetching user events (#190).**


[77cb707935a3f5d](https://github.com/gmessner/gitlab4j-api/commit/77cb707935a3f5d) Greg Messner *2018-06-11 01:39:05*


### GitHub [#192](https://github.com/gmessner/gitlab4j-api/pull/192) add project statistics information to projects api  

**add project statistics information to projects api (#192)**

 * add project statistics information to Project model

[d3788c8b33003f0](https://github.com/gmessner/gitlab4j-api/commit/d3788c8b33003f0) Jens Goldhammer *2018-06-11 00:22:06*


### GitHub [#194](https://github.com/gmessner/gitlab4j-api/issues/194) Hello! Is there an api to star a project?    *enhancement*  

**Fixed broken tests (#194).**


[8158bca12e38ca5](https://github.com/gmessner/gitlab4j-api/commit/8158bca12e38ca5) Greg Messner *2018-06-11 01:38:14*

**Added starProject() and unstarProject() (#194).**


[a5ec26fbc1304cf](https://github.com/gmessner/gitlab4j-api/commit/a5ec26fbc1304cf) Greg Messner *2018-06-11 01:11:37*


### No issue

**Fixed javadoc error.**


[074244f8fd6185a](https://github.com/gmessner/gitlab4j-api/commit/074244f8fd6185a) Greg Messner *2018-06-11 01:43:50*

**Updated for release 4.8.22**


[8a456a461204646](https://github.com/gmessner/gitlab4j-api/commit/8a456a461204646) Greg Messner *2018-06-11 01:11:02*

**Added test.**


[62e210eb2a814e7](https://github.com/gmessner/gitlab4j-api/commit/62e210eb2a814e7) Greg Messner *2018-06-11 01:09:02*


## gitlab4j-api-4.8.21
### GitHub [#187](https://github.com/gmessner/gitlab4j-api/issues/187) Adding new functionality    *enhancement*  

**Added support fopr filtering when fetching merge requests (#187, #193).**


[5e57e9fbf056478](https://github.com/gmessner/gitlab4j-api/commit/5e57e9fbf056478) Greg Messner *2018-06-10 20:08:29*

**Initial commit (#187, #193).**


[03115be432d0a50](https://github.com/gmessner/gitlab4j-api/commit/03115be432d0a50) Greg Messner *2018-06-10 19:41:10*


### GitHub [#191](https://github.com/gmessner/gitlab4j-api/pull/191) Added support for Wikis API  

**Added support for Wikis API (#191)**


[0bfdb38c2c1ea95](https://github.com/gmessner/gitlab4j-api/commit/0bfdb38c2c1ea95) Alok Shukla *2018-06-05 02:28:33*


### GitHub [#193](https://github.com/gmessner/gitlab4j-api/issues/193) API to get MR&#39;s specific to the Target branch  

**Added support fopr filtering when fetching merge requests (#187, #193).**


[5e57e9fbf056478](https://github.com/gmessner/gitlab4j-api/commit/5e57e9fbf056478) Greg Messner *2018-06-10 20:08:29*

**Initial commit (#187, #193).**


[03115be432d0a50](https://github.com/gmessner/gitlab4j-api/commit/03115be432d0a50) Greg Messner *2018-06-10 19:41:10*


### GitHub [#196](https://github.com/gmessner/gitlab4j-api/pull/196) updateUser bug  

**updateUser bug (#196)**

 * fix update user bug.
 * `skip_confirmation` should be `skip_reconfirmation` when updating user.

[961a054105a1615](https://github.com/gmessner/gitlab4j-api/commit/961a054105a1615) zhengrenjie *2018-06-10 17:18:01*


### No issue

**Updated for release 4.8.21**


[677dcf36839f150](https://github.com/gmessner/gitlab4j-api/commit/677dcf36839f150) Greg Messner *2018-06-10 20:07:54*


## gitlab4j-api-4.8.20
### GitHub [#186](https://github.com/gmessner/gitlab4j-api/pull/186) Get more information from gitlab  

**Get more information from gitlab (#186)**

 * Load forks of a project with Pager
 * Load comments of a commit with Pager
 * Load merge request changes
 * Load merge request participants.
 * Method to request repository contributors
 * getMergeRequestChanges method needs list of diffs
 * Fixed getFile method does not handle spaces in path correctly

[b96fd36b1164494](https://github.com/gmessner/gitlab4j-api/commit/b96fd36b1164494) ISibboI *2018-05-25 20:24:14*


### No issue

**Updated for release 4.8.20.**


[f1e71713bae9617](https://github.com/gmessner/gitlab4j-api/commit/f1e71713bae9617) Greg Messner *2018-05-25 22:42:22*

**Added getContributors() methods that return List.**


[07a1e4d3629b937](https://github.com/gmessner/gitlab4j-api/commit/07a1e4d3629b937) Greg Messner *2018-05-25 22:41:04*

**Added getForks() methods that return List.**


[9f32ecb94d9f67f](https://github.com/gmessner/gitlab4j-api/commit/9f32ecb94d9f67f) Greg Messner *2018-05-25 22:40:29*

**Added getParticipants() methods that return List.**


[639ca5a561bc11e](https://github.com/gmessner/gitlab4j-api/commit/639ca5a561bc11e) Greg Messner *2018-05-25 22:39:44*


## gitlab4j-api-4.8.19
### GitHub [#183](https://github.com/gmessner/gitlab4j-api/pull/183) Add personal snippets api  

**Add support for Snippets API  (#183)**


[6f5736c4a342ee5](https://github.com/gmessner/gitlab4j-api/commit/6f5736c4a342ee5) Jefferson Fausto Vaz *2018-05-10 21:04:58*


### No issue

**Added Pager and Optional support.**


[6655095e15db4d5](https://github.com/gmessner/gitlab4j-api/commit/6655095e15db4d5) Greg Messner *2018-05-10 21:46:20*

**Updated with projects formatting style.**


[ae37475b5d3dda2](https://github.com/gmessner/gitlab4j-api/commit/ae37475b5d3dda2) Greg Messner *2018-05-10 21:46:02*

**Added section for SnippetsApi.**


[8077facee6f8411](https://github.com/gmessner/gitlab4j-api/commit/8077facee6f8411) Greg Messner *2018-05-10 21:44:02*

**Updated for release 4.8.18**


[13822039edd0866](https://github.com/gmessner/gitlab4j-api/commit/13822039edd0866) Greg Messner *2018-05-09 22:37:22*


## gitlab4j-api-4.8.18
### GitHub [#182](https://github.com/gmessner/gitlab4j-api/pull/182) add extern uid field for create  

**add extern uid field for create (#182)**

 * add extern uid field for create
 * added fluent builder pattern to User

[cabccb49a77f4cc](https://github.com/gmessner/gitlab4j-api/commit/cabccb49a77f4cc) David Lam *2018-05-09 06:45:32*


### No issue

**Added updateUser() and javadoc cleanup.**


[a260cd3dcef55b1](https://github.com/gmessner/gitlab4j-api/commit/a260cd3dcef55b1) Greg Messner *2018-05-09 16:25:05*


## gitlab4j-api-4.8.17
### GitHub [#179](https://github.com/gmessner/gitlab4j-api/pull/179) Controlled Pagination for Runners    *enhancement*  

**Controlled Pagination for Runners (#179)**


[bab4bb6aa80f048](https://github.com/gmessner/gitlab4j-api/commit/bab4bb6aa80f048) David Lam *2018-05-01 02:23:33*


### No issue

**Fixed Javadoc issue.**


[3638ec0ff99ea23](https://github.com/gmessner/gitlab4j-api/commit/3638ec0ff99ea23) Greg Messner *2018-05-01 02:32:20*

**Updated for release 4.8.17.**


[7d79b8118a94720](https://github.com/gmessner/gitlab4j-api/commit/7d79b8118a94720) Greg Messner *2018-05-01 02:27:52*


## gitlab4j-api-4.8.16
### No issue

**Updated for 4.8.16**


[0edf0999a3be817](https://github.com/gmessner/gitlab4j-api/commit/0edf0999a3be817) Greg Messner *2018-04-27 04:58:02*

**Fixed createPipeline, was looking for OK instead of CREATED.**


[4c014541d1eaaa5](https://github.com/gmessner/gitlab4j-api/commit/4c014541d1eaaa5) Greg Messner *2018-04-27 04:57:12*


## gitlab4j-api-4.8.15
### GitHub [#177](https://github.com/gmessner/gitlab4j-api/issues/177) support project&#39;s events with v4  

**Mods to support new fields in v4 project events (#177).**


[3b1f02eb67f0443](https://github.com/gmessner/gitlab4j-api/commit/3b1f02eb67f0443) Greg Messner *2018-04-25 04:39:36*

**Initial commit (#177).**


[8b1d9863c6e0030](https://github.com/gmessner/gitlab4j-api/commit/8b1d9863c6e0030) Greg Messner *2018-04-25 04:35:44*


### No issue

**Updated for release 4.8.15**


[43b1374e1878a0c](https://github.com/gmessner/gitlab4j-api/commit/43b1374e1878a0c) Greg Messner *2018-04-25 04:40:40*


## gitlab4j-api-4.8.14
### GitHub [#175](https://github.com/gmessner/gitlab4j-api/issues/175) Services API &quot;JIRA&quot;    *enhancement*  

**Mods to support new services (#175, #176).**


[28be1a729cefb67](https://github.com/gmessner/gitlab4j-api/commit/28be1a729cefb67) Greg Messner *2018-04-24 01:58:50*

**INitial commit (#175, #176).**


[0aca947c20f40ac](https://github.com/gmessner/gitlab4j-api/commit/0aca947c20f40ac) Greg Messner *2018-04-24 01:56:12*

**Fixed typo (#175).**


[d98ba0af8b0bc8b](https://github.com/gmessner/gitlab4j-api/commit/d98ba0af8b0bc8b) Greg Messner *2018-04-23 07:06:30*

**Mods to support Slack and JIRA services (#175 and #176).**


[4d79e72b9370733](https://github.com/gmessner/gitlab4j-api/commit/4d79e72b9370733) Greg Messner *2018-04-23 06:58:40*

**Initial commit (#175 and #176).**


[ab4861006be3991](https://github.com/gmessner/gitlab4j-api/commit/ab4861006be3991) Greg Messner *2018-04-23 06:58:01*

**Initial commit (#175).**


[fb1f0a9f8d8227a](https://github.com/gmessner/gitlab4j-api/commit/fb1f0a9f8d8227a) Greg Messner *2018-04-23 06:56:45*


### GitHub [#176](https://github.com/gmessner/gitlab4j-api/issues/176) Services API &quot;Slack notifications&quot;    *enhancement*  

**Mods to support new services (#175, #176).**


[28be1a729cefb67](https://github.com/gmessner/gitlab4j-api/commit/28be1a729cefb67) Greg Messner *2018-04-24 01:58:50*

**INitial commit (#175, #176).**


[0aca947c20f40ac](https://github.com/gmessner/gitlab4j-api/commit/0aca947c20f40ac) Greg Messner *2018-04-24 01:56:12*

**Mods to support Slack and JIRA services (#175 and #176).**


[4d79e72b9370733](https://github.com/gmessner/gitlab4j-api/commit/4d79e72b9370733) Greg Messner *2018-04-23 06:58:40*

**Initial commit (#175 and #176).**


[ab4861006be3991](https://github.com/gmessner/gitlab4j-api/commit/ab4861006be3991) Greg Messner *2018-04-23 06:58:01*

**Initial commit (#176).**


[233492329b7726a](https://github.com/gmessner/gitlab4j-api/commit/233492329b7726a) Greg Messner *2018-04-23 06:55:37*


### No issue

**Fixed javadoc issue.**


[c2407dc2ab18c29](https://github.com/gmessner/gitlab4j-api/commit/c2407dc2ab18c29) Greg Messner *2018-04-24 02:01:47*

**Mods to bring in-line with GitLab API documentation.**


[d4abafc318749c3](https://github.com/gmessner/gitlab4j-api/commit/d4abafc318749c3) Greg Messner *2018-04-24 01:57:35*

**Updated for release 4.8.14**


[54a36706673b01d](https://github.com/gmessner/gitlab4j-api/commit/54a36706673b01d) Greg Messner *2018-04-24 01:55:15*

**Initial commit.**


[6028d80ed00ba5b](https://github.com/gmessner/gitlab4j-api/commit/6028d80ed00ba5b) Greg Messner *2018-04-23 06:57:16*


## gitlab4j-api-4.8.12
### GitHub [#159](https://github.com/gmessner/gitlab4j-api/pull/159) Health Check Api    *enhancement*  

**Added unit tests for HealthCheckApi (#159).**


[9f75e8023a515f0](https://github.com/gmessner/gitlab4j-api/commit/9f75e8023a515f0) Greg Messner *2018-04-20 07:03:43*

**Health Check Api (#159)**

 * Added Health Check API support

[900c1a91beba7eb](https://github.com/gmessner/gitlab4j-api/commit/900c1a91beba7eb) David Lam *2018-04-20 06:02:45*


## gitlab4j-api-4.8.11
### GitHub [#174](https://github.com/gmessner/gitlab4j-api/issues/174) Project API &quot;merge_method&quot;    *enhancement*  

**Mods to support merge_method (#174).**


[8c58e9302e9c7b3](https://github.com/gmessner/gitlab4j-api/commit/8c58e9302e9c7b3) Greg Messner *2018-04-19 23:15:12*


### No issue

**Updated for release 4.8.11**


[31d27e27cd3719e](https://github.com/gmessner/gitlab4j-api/commit/31d27e27cd3719e) Greg Messner *2018-04-20 00:43:32*

**Mods so unit tests work correctly when using the release plugin.**


[80cd60550cce3a1](https://github.com/gmessner/gitlab4j-api/commit/80cd60550cce3a1) Greg Messner *2018-04-15 18:01:39*


## gitlab4j-api-4.8.10
### GitHub [#171](https://github.com/gmessner/gitlab4j-api/issues/171) Project API &quot;Upload a file&quot; and &quot;Push Rules&quot;    *enhancement*  

**Mods for file upload and push rules (#171).**


[05e5f53cc92cd52](https://github.com/gmessner/gitlab4j-api/commit/05e5f53cc92cd52) Greg Messner *2018-04-15 04:55:13*

**Mods to support file upload (#171).**


[03d69b81887ebfd](https://github.com/gmessner/gitlab4j-api/commit/03d69b81887ebfd) Greg Messner *2018-04-15 04:05:55*

**Initial commit (#171).**


[f1a738329f38436](https://github.com/gmessner/gitlab4j-api/commit/f1a738329f38436) Greg Messner *2018-04-15 04:05:13*


### GitHub [#172](https://github.com/gmessner/gitlab4j-api/pull/172)  Implemented being able to set a tag_list when creating and updating a project.    *enhancement*  

**Now throws exception when tag lists are referenced when using the V3 API (#172).**


[b8ed1ba88dd609d](https://github.com/gmessner/gitlab4j-api/commit/b8ed1ba88dd609d) Greg Messner *2018-04-15 05:31:25*

**Merging tag list related mods from PR #172**


[358f4221d906c35](https://github.com/gmessner/gitlab4j-api/commit/358f4221d906c35) Greg Messner *2018-04-15 05:12:32*

**Implemented being able to set a tag_list when creating and updating a (#172)**


[865a86399b0f753](https://github.com/gmessner/gitlab4j-api/commit/865a86399b0f753) jimrinhealthcare *2018-04-15 05:04:02*


### GitHub [#173](https://github.com/gmessner/gitlab4j-api/pull/173) using post method instead of get to enable a runner    *bug*  

**No correctly uses POST instead of GET to enable a runner (#173)**


[a273f6740c42000](https://github.com/gmessner/gitlab4j-api/commit/a273f6740c42000) Fouad *2018-04-14 21:27:53*


## gitlab4j-api-4.8.9
### GitHub [#167](https://github.com/gmessner/gitlab4j-api/pull/167) Add a variation of the getCommits method that supports Paging and a path    *enhancement*  

**Add a variation of the getCommits method that supports Paging and a path (#167)**

 * Add a variation of the getCommits() method that supports Paging and a path.  A simple variation to make it easy to find all commits for a given file.

[94b37c5334a5d81](https://github.com/gmessner/gitlab4j-api/commit/94b37c5334a5d81) Sue Wilson *2018-04-04 23:38:24*


### GitHub [#169](https://github.com/gmessner/gitlab4j-api/issues/169) userApi.blockUser(Int) is trying to read response as User.class instead   

**Mods related to blockUser() and unblockUser() fixes (#169).**


[a851e3ac54b27ab](https://github.com/gmessner/gitlab4j-api/commit/a851e3ac54b27ab) Greg Messner *2018-04-05 01:02:40*


### No issue

**Updated for releae 4.8.9**


[057104a3b3b0470](https://github.com/gmessner/gitlab4j-api/commit/057104a3b3b0470) Greg Messner *2018-04-05 01:03:43*


## gitlab4j-api-4.8.8
### GitHub [#163](https://github.com/gmessner/gitlab4j-api/issues/163) Error while getting the AccessLevel  

**Added handling and logging of invalid access levels (#163).**


[23b21c946e7c0fa](https://github.com/gmessner/gitlab4j-api/commit/23b21c946e7c0fa) Greg Messner *2018-04-02 04:37:23*


### GitHub [#164](https://github.com/gmessner/gitlab4j-api/issues/164) Support for &quot;Closed At&quot; for issues    *enhancement*  

**Mods to support Issue clased_at property (#164).**


[f252531ea4e22bb](https://github.com/gmessner/gitlab4j-api/commit/f252531ea4e22bb) Greg Messner *2018-04-02 04:20:15*


### No issue

**Updated for release 4.8.8**


[5d205c5c885cdde](https://github.com/gmessner/gitlab4j-api/commit/5d205c5c885cdde) Greg Messner *2018-04-02 04:38:14*


## gitlab4j-api-4.8.7
### GitHub [#154](https://github.com/gmessner/gitlab4j-api/pull/154) Runners API  

**Fixed javadoc errors (#154).**


[2a4e517b97db4ce](https://github.com/gmessner/gitlab4j-api/commit/2a4e517b97db4ce) Greg Messner *2018-03-14 04:19:53*

**Added missing fields (#154).**


[66741875fc52453](https://github.com/gmessner/gitlab4j-api/commit/66741875fc52453) Greg Messner *2018-03-14 04:02:41*

**Changed is_shared to isShared (#154).**


[91a0ec455ccdef9](https://github.com/gmessner/gitlab4j-api/commit/91a0ec455ccdef9) Greg Messner *2018-03-14 04:02:21*

**Added bean test for Runner and RunnerDetail (#154).**


[bc4b9abdddd36e7](https://github.com/gmessner/gitlab4j-api/commit/bc4b9abdddd36e7) Greg Messner *2018-03-14 04:01:20*

**Initial commit (#154).**


[3e0b7af33719082](https://github.com/gmessner/gitlab4j-api/commit/3e0b7af33719082) Greg Messner *2018-03-14 04:00:24*

**Runners API (#154)**

 * Added RunnersApi implementation and tests.

[ff2844999b11186](https://github.com/gmessner/gitlab4j-api/commit/ff2844999b11186) David Lam *2018-03-14 02:06:46*


### GitHub [#156](https://github.com/gmessner/gitlab4j-api/pull/156) Feature/protected branch and example test properties  

**Feature/protected branch and example test properties (#156)**

 * Minor mods to protected branch functionality.
 * Cleaned up example-test-gitlab4j.properties and related tests.

[226792226c15d01](https://github.com/gmessner/gitlab4j-api/commit/226792226c15d01) Greg Messner *2018-03-12 18:08:23*


### GitHub [#157](https://github.com/gmessner/gitlab4j-api/pull/157) Use secure passwords for OAuth2 logins    *enhancement*  

**Use secure passwords for OAuth2 logins (#157)**


[8e00f6e05c53cf6](https://github.com/gmessner/gitlab4j-api/commit/8e00f6e05c53cf6) Greg Messner *2018-03-13 10:44:06*


### No issue

**Fixed formatting.**


[4af2f7f2fc898f9](https://github.com/gmessner/gitlab4j-api/commit/4af2f7f2fc898f9) Greg Messner *2018-03-14 04:05:41*

**Fixed typo.**


[6098c820d38e7f1](https://github.com/gmessner/gitlab4j-api/commit/6098c820d38e7f1) Greg Messner *2018-03-14 02:12:29*

**Updates for release 4.8.7**


[259dcad68d09eb6](https://github.com/gmessner/gitlab4j-api/commit/259dcad68d09eb6) Greg Messner *2018-03-14 02:10:40*

**Replaced all instances of str.length() == 0 with str.isEmpty() per lamdaV suggestion.**


[00d2e911845475d](https://github.com/gmessner/gitlab4j-api/commit/00d2e911845475d) Greg Messner *2018-03-13 00:33:23*


## gitlab4j-api-4.8.6
### GitHub [#151](https://github.com/gmessner/gitlab4j-api/issues/151) failed to map merge request event string because of date format parse error  

**Added handling of invalid XSD dates that GitLab might use (#151).**


[7cdbcaff08eaf54](https://github.com/gmessner/gitlab4j-api/commit/7cdbcaff08eaf54) Greg Messner *2018-03-12 07:11:59*

**Initial commit (#151).**


[04d7e085486e5b5](https://github.com/gmessner/gitlab4j-api/commit/04d7e085486e5b5) Greg Messner *2018-03-12 07:11:48*


### GitHub [#152](https://github.com/gmessner/gitlab4j-api/pull/152) Project archiving and unarchiving  

**Project archiving and unarchiving (#152)**

 * project archiving and unarchiving
 * expected response for archive and unarchive fixed
 * remove unnecessary preconditions for archiving test

[78c69df7ede9bd8](https://github.com/gmessner/gitlab4j-api/commit/78c69df7ede9bd8) Michal Augustýn *2018-03-09 02:06:23*


### GitHub [#153](https://github.com/gmessner/gitlab4j-api/pull/153) Project Branches API  

**Project Branches API (#153)**

 * added new protected branch API
 * updated README

[5a887c9540928b8](https://github.com/gmessner/gitlab4j-api/commit/5a887c9540928b8) David Lam *2018-03-11 21:58:28*


## gitlab4j-api-4.8.5
### GitHub [#147](https://github.com/gmessner/gitlab4j-api/pull/147) PipelineStatus MANUAL  

**A pipeline that is waiting for a manual step to be executed will get the status manual (#147)**


[aecffa42912b86f](https://github.com/gmessner/gitlab4j-api/commit/aecffa42912b86f) pimtegelaar *2018-03-01 15:29:36*


### GitHub [#148](https://github.com/gmessner/gitlab4j-api/pull/148) Support for more parameters in createMergeRequest method  

**Support additional parameters in createMergeRequest method (#148)**


[d492daecd3c4cc9](https://github.com/gmessner/gitlab4j-api/commit/d492daecd3c4cc9) Michal Augustýn *2018-03-03 20:53:33*


### GitHub [#149](https://github.com/gmessner/gitlab4j-api/issues/149) failed to map MergeRequest note event string to Object    *bug*  

**Fixed data binding for NoteableType (#149).**


[422db6b43872a93](https://github.com/gmessner/gitlab4j-api/commit/422db6b43872a93) Greg Messner *2018-03-06 04:14:08*

**Initial commit (#149).**


[46b72ec7972d48f](https://github.com/gmessner/gitlab4j-api/commit/46b72ec7972d48f) Greg Messner *2018-03-06 04:13:25*


### No issue

**Updated for release 4.8.5**


[05125dd3ac0e7a2](https://github.com/gmessner/gitlab4j-api/commit/05125dd3ac0e7a2) Greg Messner *2018-03-06 04:17:10*


## gitlab4j-api-4.8.4
### GitHub [#143](https://github.com/gmessner/gitlab4j-api/issues/143) Notification settings API?  

**Added info on the NotificationSettingsApi (#143).**


[031fdeb8a2e2a53](https://github.com/gmessner/gitlab4j-api/commit/031fdeb8a2e2a53) Greg Messner *2018-02-23 04:37:45*

**Initial commit (#143).**


[0e6a72a8c0e26d8](https://github.com/gmessner/gitlab4j-api/commit/0e6a72a8c0e26d8) Greg Messner *2018-02-23 04:30:31*

**Added tests for NotificationSettings (#143).**


[40a846ff058fa86](https://github.com/gmessner/gitlab4j-api/commit/40a846ff058fa86) Greg Messner *2018-02-23 04:30:11*


### No issue

**Updated for release 4.8.3**


[a252e37e6387461](https://github.com/gmessner/gitlab4j-api/commit/a252e37e6387461) Greg Messner *2018-02-17 22:53:54*


## gitlab4j-api-4.8.3
### GitHub [#140](https://github.com/gmessner/gitlab4j-api/issues/140) Merge request    *enhancement*  

**Added tests for merge request notes (#140).**


[6a0d9bb89131c01](https://github.com/gmessner/gitlab4j-api/commit/6a0d9bb89131c01) Greg Messner *2018-02-17 22:45:53*

**Added support for merge request notes (#140).**


[54e58dc111b939b](https://github.com/gmessner/gitlab4j-api/commit/54e58dc111b939b) Greg Messner *2018-02-17 17:26:26*


## gitlab4j-api-4.8.2
### GitHub [#137](https://github.com/gmessner/gitlab4j-api/pull/137) Fixed update file API response code.  

**Fixed update file API response code. (#137)**


[1f9055a2c35f856](https://github.com/gmessner/gitlab4j-api/commit/1f9055a2c35f856) Sergey Isterin *2018-02-05 16:06:36*


### GitHub [#138](https://github.com/gmessner/gitlab4j-api/pull/138) changed comparison of Integer references using the == to equals()   

**changed comparison of Integer references using the == to equals()  (#138)**

 * Changed comparison of Integer references using the == to equals()

[832cbb579f52ae2](https://github.com/gmessner/gitlab4j-api/commit/832cbb579f52ae2) Sergey Isterin *2018-02-06 06:42:39*


### GitHub [#139](https://github.com/gmessner/gitlab4j-api/pull/139) Fixed Double-Checked Locking by declaring fields to be volatile  

**Mark gitLabApi final to make the class immutable (#139).**


[e40e9171365d8b9](https://github.com/gmessner/gitlab4j-api/commit/e40e9171365d8b9) Greg Messner *2018-02-10 03:31:00*


### GitHub [#141](https://github.com/gmessner/gitlab4j-api/issues/141) Working with Proxy    *enhancement*  

**Updated section on using a proxy server (#141).**


[a5c86202532007d](https://github.com/gmessner/gitlab4j-api/commit/a5c86202532007d) Greg Messner *2018-02-10 21:08:19*

**Mods to support using a proxy server (#141).**


[7af51b8665e0f83](https://github.com/gmessner/gitlab4j-api/commit/7af51b8665e0f83) Greg Messner *2018-02-10 20:58:15*

**Initial commit (#141).**


[cff589a953670a4](https://github.com/gmessner/gitlab4j-api/commit/cff589a953670a4) Greg Messner *2018-02-10 18:59:38*


### No issue

**Added getOptionalMergeRequest().**


[d8f9b2f10f8863c](https://github.com/gmessner/gitlab4j-api/commit/d8f9b2f10f8863c) Greg Messner *2018-02-10 21:23:29*


## gitlab4j-api-4.8.1
### GitHub [#127](https://github.com/gmessner/gitlab4j-api/issues/127) getXY throws exception if XY not exists  

**Added Optional<> support (#127).**


[ebc2eb049a76466](https://github.com/gmessner/gitlab4j-api/commit/ebc2eb049a76466) Greg Messner *2018-01-29 04:13:39*

**Added Optional<> support (#127).**


[bcd278bb8b6580a](https://github.com/gmessner/gitlab4j-api/commit/bcd278bb8b6580a) Greg Messner *2018-01-29 04:04:08*

**Added section on Optional<T> use (#127).**


[c666e172614d598](https://github.com/gmessner/gitlab4j-api/commit/c666e172614d598) Greg Messner *2018-01-28 20:35:41*


### GitHub [#135](https://github.com/gmessner/gitlab4j-api/pull/135) Adds missing &quot;MANUAL&quot; job status    *bug*  

**Adds missing "MANUAL" job status (#135)**


[f5a54d5a0bc1009](https://github.com/gmessner/gitlab4j-api/commit/f5a54d5a0bc1009) Matthias Huttar *2018-02-03 08:11:12*


### GitHub [#136](https://github.com/gmessner/gitlab4j-api/issues/136) RepositoryFile.setContent seems to truncate the last eol     *bug*  

**Changed processing of whitespace on content properties (#136).**


[c3b9c327a448c11](https://github.com/gmessner/gitlab4j-api/commit/c3b9c327a448c11) Greg Messner *2018-02-03 08:17:42*

**Changed processing of whitespace on content properties (#136).**


[0abb1d217a2c28c](https://github.com/gmessner/gitlab4j-api/commit/0abb1d217a2c28c) Greg Messner *2018-02-03 08:13:41*


### No issue

**Updated for release 4.8.1**


[9b2b383ed262598](https://github.com/gmessner/gitlab4j-api/commit/9b2b383ed262598) Greg Messner *2018-02-03 08:15:34*

**Fixed compiler warnings.**


[02f1b7ffc02520d](https://github.com/gmessner/gitlab4j-api/commit/02f1b7ffc02520d) Greg Messner *2018-01-29 04:03:19*

**Fixed version on Maven pom.xml section.**


[eb608e33cc81c50](https://github.com/gmessner/gitlab4j-api/commit/eb608e33cc81c50) Greg Messner *2018-01-28 21:44:39*

**Corrected section on Java 8 Optional<T> support.**


[210b4770eb25842](https://github.com/gmessner/gitlab4j-api/commit/210b4770eb25842) Greg Messner *2018-01-28 20:37:12*


## gitlab4j-api-4.8.0
### GitHub [#127](https://github.com/gmessner/gitlab4j-api/issues/127) getXY throws exception if XY not exists  

**Fixed Javadoc warnings (#127).**


[04a59c57dededb8](https://github.com/gmessner/gitlab4j-api/commit/04a59c57dededb8) Greg Messner *2018-01-28 02:43:17*

**Mods for Java 8 (#127).**


[62f4e18559044c9](https://github.com/gmessner/gitlab4j-api/commit/62f4e18559044c9) Greg Messner *2018-01-28 02:34:46*

**Mods for Java 8 and for new getOptionalXyz() methods (#127).**


[ec18994d24867ac](https://github.com/gmessner/gitlab4j-api/commit/ec18994d24867ac) Greg Messner *2018-01-28 02:30:51*


### GitHub [#130](https://github.com/gmessner/gitlab4j-api/issues/130) Outdated Webhook does not support &quot;Job Hook&quot; rename  

**Support New "Job Hook" Hook Renaming (#131)**

 * fix #130 support new hook name
 * rename BuildEvent consts to be more descriptive

[5487b3d3eb97369](https://github.com/gmessner/gitlab4j-api/commit/5487b3d3eb97369) Adam Snyder *2018-01-22 18:37:31*


### GitHub [#131](https://github.com/gmessner/gitlab4j-api/pull/131) Support New &quot;Job Hook&quot; Hook Renaming  

**Support New "Job Hook" Hook Renaming (#131)**

 * fix #130 support new hook name
 * rename BuildEvent consts to be more descriptive

[5487b3d3eb97369](https://github.com/gmessner/gitlab4j-api/commit/5487b3d3eb97369) Adam Snyder *2018-01-22 18:37:31*


### GitHub [#134](https://github.com/gmessner/gitlab4j-api/issues/134) Email field for member doesn&#39;t exist  

**Removed email property (#134).**


[8d0ddc1c66d2187](https://github.com/gmessner/gitlab4j-api/commit/8d0ddc1c66d2187) Greg Messner *2018-01-23 02:54:57*


## gitlab4j-api-4.7.17
### GitHub [#129](https://github.com/gmessner/gitlab4j-api/issues/129) Does not work in Java 9    *bug*  

**Added javax.activation as a dependency (#129).**


[624f7fb5ad78bae](https://github.com/gmessner/gitlab4j-api/commit/624f7fb5ad78bae) Greg Messner *2018-01-18 03:57:26*


### No issue

**Updated for release 4.7.17**


[076ac3d9ad32f2c](https://github.com/gmessner/gitlab4j-api/commit/076ac3d9ad32f2c) Greg Messner *2018-01-18 03:59:12*


## gitlab4j-api-4.7.16
### GitHub [#128](https://github.com/gmessner/gitlab4j-api/issues/128) Replacement for login method    *enhancement*  

**Fixed Javadoc errors (#128).**


[5e2ba35cd8436e9](https://github.com/gmessner/gitlab4j-api/commit/5e2ba35cd8436e9) Greg Messner *2018-01-16 19:16:35*

**Clarified Javadocs related to the various login methods (#128).**


[11f870d4cd9acda](https://github.com/gmessner/gitlab4j-api/commit/11f870d4cd9acda) Greg Messner *2018-01-16 19:07:04*


### GitHub [#129](https://github.com/gmessner/gitlab4j-api/issues/129) Does not work in Java 9    *bug*  

**Added javax.xml.bind 2.3.0 dependency to fix Java 9 issue (#129).**


[e65e88205368df5](https://github.com/gmessner/gitlab4j-api/commit/e65e88205368df5) Greg Messner *2018-01-16 19:05:14*


### No issue

**Updated for release 4.7.16**


[917be7e506fb36c](https://github.com/gmessner/gitlab4j-api/commit/917be7e506fb36c) Greg Messner *2018-01-16 19:07:56*


## gitlab4j-api-4.7.15
### GitHub [#125](https://github.com/gmessner/gitlab4j-api/pull/125) Rework exception handling to attach original exception.  

**Rework exception handling to attach original exception. (#125)**

 * Rework exception handling so that original exception is attached to GitlabApiException.
 * Simplify exception handling, but keeping relevant data.
 * Remove unused imports

[320e88c9e9d2f62](https://github.com/gmessner/gitlab4j-api/commit/320e88c9e9d2f62) Tiaan Louw *2018-01-12 14:44:48*


### GitHub [#126](https://github.com/gmessner/gitlab4j-api/issues/126) Support for subgroup endpoint    *enhancement*  

**Added support to fetch subgroups (#126).**


[03de914186d8cce](https://github.com/gmessner/gitlab4j-api/commit/03de914186d8cce) Greg Messner *2018-01-12 16:16:10*


### No issue

**Updated for 4.7.15**


[d4f589cc4fc600c](https://github.com/gmessner/gitlab4j-api/commit/d4f589cc4fc600c) Greg Messner *2018-01-12 16:17:00*

**Removed blank lines.**


[af23f9973e6db5c](https://github.com/gmessner/gitlab4j-api/commit/af23f9973e6db5c) Greg Messner *2018-01-12 16:15:05*

**Simplified exception handling and logging of same.**


[2a4affefac13008](https://github.com/gmessner/gitlab4j-api/commit/2a4affefac13008) Greg Messner *2018-01-11 16:05:18*


## gitlab4j-api-4.7.14
### GitHub [#114](https://github.com/gmessner/gitlab4j-api/issues/114) timestat back and forth is weird    *bug*  

**Updated for release 4.7.12 (#114).**


[a4cb65445b7dc23](https://github.com/gmessner/gitlab4j-api/commit/a4cb65445b7dc23) Greg Messner *2018-01-07 17:59:03*


### GitHub [#117](https://github.com/gmessner/gitlab4j-api/issues/117) System hooks API isn&#39;t exposed    *enhancement*  

**Updated for release 4.7.14 (#117).**


[d4cef4aa26225aa](https://github.com/gmessner/gitlab4j-api/commit/d4cef4aa26225aa) Greg Messner *2018-01-08 03:54:40*

**Mods to support the new SystemHooksApi (#117).**


[3926451af077f46](https://github.com/gmessner/gitlab4j-api/commit/3926451af077f46) Greg Messner *2018-01-08 03:54:15*


## gitlab4j-api-4.7.12
### GitHub [#114](https://github.com/gmessner/gitlab4j-api/issues/114) timestat back and forth is weird    *bug*  

**Fixed issue with dropping days (#114).**


[81cc979193e5b90](https://github.com/gmessner/gitlab4j-api/commit/81cc979193e5b90) Greg Messner *2018-01-07 17:44:27*


### GitHub [#117](https://github.com/gmessner/gitlab4j-api/issues/117) System hooks API isn&#39;t exposed    *enhancement*  

**Initial commit in support of System Hooks API (#117).**


[0ab14a6733c4e6a](https://github.com/gmessner/gitlab4j-api/commit/0ab14a6733c4e6a) Greg Messner *2018-01-07 00:19:03*

**Added support for System Hooks API (#117).**


[a9fb9b335af957f](https://github.com/gmessner/gitlab4j-api/commit/a9fb9b335af957f) Greg Messner *2018-01-07 00:17:46*


### No issue

**Removed unnecessary synchronization on toString().**


[a1bb0e650fc666e](https://github.com/gmessner/gitlab4j-api/commit/a1bb0e650fc666e) Greg Messner *2018-01-07 06:54:12*


## gitlab4j-api-4.7.11
### GitHub [#114](https://github.com/gmessner/gitlab4j-api/issues/114) timestat back and forth is weird    *bug*  

**Fixed Javados on toString() (#114).**


[586cc89e609f6fc](https://github.com/gmessner/gitlab4j-api/commit/586cc89e609f6fc) Greg Messner *2018-01-06 06:53:01*

**Fixed bug with GitLab where 1mo = 30d (#114).**


[85ad0d8483e6f67](https://github.com/gmessner/gitlab4j-api/commit/85ad0d8483e6f67) Greg Messner *2018-01-05 23:14:02*


### GitHub [#122](https://github.com/gmessner/gitlab4j-api/issues/122) How to create a commit with multiple files and actions with java-api    *enhancement*  

**Javadoc fixes (#122).**


[ddc98a7aeaa08a3](https://github.com/gmessner/gitlab4j-api/commit/ddc98a7aeaa08a3) Greg Messner *2018-01-03 09:06:10*

**Added support for batch commit creation (#122).**


[69ce3604f5ab78c](https://github.com/gmessner/gitlab4j-api/commit/69ce3604f5ab78c) Greg Messner *2018-01-03 09:01:34*

**Initial commit (#122).**


[1929d5d2504c02b](https://github.com/gmessner/gitlab4j-api/commit/1929d5d2504c02b) Greg Messner *2018-01-03 09:00:59*


### GitHub [#123](https://github.com/gmessner/gitlab4j-api/issues/123) please add a overloading for delete user API     *enhancement*  

**Added hard_delete support to deleteUser() (#123).**


[036fa56da3d4ed6](https://github.com/gmessner/gitlab4j-api/commit/036fa56da3d4ed6) Greg Messner *2018-01-03 09:23:32*


### GitHub [#124](https://github.com/gmessner/gitlab4j-api/issues/124) printing_merge_request_link_enabled flag for ProjectApi    *enhancement*  

**Added support for printing_merge_request_link_enabled when creating and updating a project (#124).**


[d03c2d2138098c9](https://github.com/gmessner/gitlab4j-api/commit/d03c2d2138098c9) Greg Messner *2018-01-06 07:02:51*


### No issue

**Updated for release 4.7.10**


[964df9cac6abad5](https://github.com/gmessner/gitlab4j-api/commit/964df9cac6abad5) Greg Messner *2018-01-06 07:04:16*

**Updated for release 4.7.10**


[3f0c1d091431dbd](https://github.com/gmessner/gitlab4j-api/commit/3f0c1d091431dbd) Greg Messner *2018-01-03 04:48:16*


## gitlab4j-api-4.7.10
### GitHub [#121](https://github.com/gmessner/gitlab4j-api/issues/121) org.gitlab4j.api.GitLabApiException: 404 Not found while calling acceptMergeRequests    *enhancement*  

**Updated Javadoc to make it clear that the internal ID is used for merge requests (#121).**


[90ad7583d0ccd57](https://github.com/gmessner/gitlab4j-api/commit/90ad7583d0ccd57) Greg Messner *2018-01-03 04:42:10*

**Updated Javadoc to make it clear that the internal ID is used for merge requests (#121).**


[3509018454ee5bb](https://github.com/gmessner/gitlab4j-api/commit/3509018454ee5bb) Greg Messner *2018-01-02 16:19:45*


## gitlab4j-api-4.7.9
### GitHub [#115](https://github.com/gmessner/gitlab4j-api/issues/115) I cannot get the javax.ws.rs to download using ivy or sbt (solved)  

**Fixed formatting of Ivy and SBT note (#115).**


[7eb714cd6f134fb](https://github.com/gmessner/gitlab4j-api/commit/7eb714cd6f134fb) Greg Messner *2017-12-29 02:44:30*

**Added note about Ivy and SBT (#115)**


[35408f67ef7bacd](https://github.com/gmessner/gitlab4j-api/commit/35408f67ef7bacd) Greg Messner *2017-12-29 02:43:03*


### GitHub [#116](https://github.com/gmessner/gitlab4j-api/issues/116) It is impossible to understand what user SshKey belongs to    *enhancement*  

**Added support for user ID in SshKey class (#116).**


[4bb906b9763f089](https://github.com/gmessner/gitlab4j-api/commit/4bb906b9763f089) Greg Messner *2018-12-31 02:53:07*


### GitHub [#119](https://github.com/gmessner/gitlab4j-api/issues/119) Unable to get authToken/tokenType from existing GitLabApi instance    *enhancement*  

**Added duplicate() method and token info getters (#119).**


[fb4d1759107827c](https://github.com/gmessner/gitlab4j-api/commit/fb4d1759107827c) Greg Messner *2018-12-31 02:44:04*

**Added methods to get authToken, authType, and secretToken (#119).**


[5fbd9049344c1c1](https://github.com/gmessner/gitlab4j-api/commit/5fbd9049344c1c1) Greg Messner *2018-12-31 00:18:45*


### GitHub [#120](https://github.com/gmessner/gitlab4j-api/issues/120) gitlab4j-api isn&#39;t thread-safe?    *bug*  *enhancement*  

**Now thread safe (#120).**


[b0e1aac89d8a1ee](https://github.com/gmessner/gitlab4j-api/commit/b0e1aac89d8a1ee) Greg Messner *2018-12-31 00:15:31*


### No issue

**Updated for release 4.7.9**


[373f110ad269ebb](https://github.com/gmessner/gitlab4j-api/commit/373f110ad269ebb) Greg Messner *2018-12-31 02:52:23*

**Fixed formatting of time estimate section.**


[ef0fb73d8d15a41](https://github.com/gmessner/gitlab4j-api/commit/ef0fb73d8d15a41) Greg Messner *2017-12-29 02:33:58*


## gitlab4j-api-4.7.8
### GitHub [#114](https://github.com/gmessner/gitlab4j-api/issues/114) timestat back and forth is weird    *bug*  

**Fixed time estimate parsing to follow GitLab standards (#114).**


[6c7231f139c33f8](https://github.com/gmessner/gitlab4j-api/commit/6c7231f139c33f8) Greg Messner *2017-12-29 02:14:28*


### No issue

**Removed note about logging in on 10.2 servers.**


[c4721cc503357bd](https://github.com/gmessner/gitlab4j-api/commit/c4721cc503357bd) Greg Messner *2017-12-29 00:46:53*

**Added headers to all sections.**


[c1c4cf5b4aed977](https://github.com/gmessner/gitlab4j-api/commit/c1c4cf5b4aed977) Greg Messner *2017-12-29 00:42:49*

**Added test of OauthTokenResponse bean.**


[38a0df396c93b84](https://github.com/gmessner/gitlab4j-api/commit/38a0df396c93b84) Greg Messner *2017-12-28 19:47:10*

**Added remove() method.**


[7881a1bb297c011](https://github.com/gmessner/gitlab4j-api/commit/7881a1bb297c011) Greg Messner *2017-12-28 17:23:40*


## gitlab4j-api-4.7.7
### GitHub [#111](https://github.com/gmessner/gitlab4j-api/issues/111) Reimplement login/password authentication via OAuth2    *enhancement*  

**Mods to support OAuth2 login (#111).**


[507a7e7720bf120](https://github.com/gmessner/gitlab4j-api/commit/507a7e7720bf120) Greg Messner *2017-12-27 23:49:55*

**Initial commit (#111).**


[fe2552ef198230c](https://github.com/gmessner/gitlab4j-api/commit/fe2552ef198230c) Greg Messner *2017-12-27 23:49:31*

**Renamed TestGitLabLogin.java (#111).**


[30718646a63df55](https://github.com/gmessner/gitlab4j-api/commit/30718646a63df55) Greg Messner *2017-12-27 23:48:49*


### GitHub [#112](https://github.com/gmessner/gitlab4j-api/issues/112) No api for sharing project with group    *enhancement*  

**Added share project support (#112).**


[6753e62fa6a9d1a](https://github.com/gmessner/gitlab4j-api/commit/6753e62fa6a9d1a) Greg Messner *2017-12-27 20:02:40*


### GitHub [#113](https://github.com/gmessner/gitlab4j-api/issues/113) Successful GroupApi.removeMember throws GitLabApiException  

**Initial commit (#113).**


[1790d4db0a45746](https://github.com/gmessner/gitlab4j-api/commit/1790d4db0a45746) Greg Messner *2017-12-27 19:21:28*


### No issue

**Updated for 4.7.7**


[7c243ba37542e68](https://github.com/gmessner/gitlab4j-api/commit/7c243ba37542e68) Greg Messner *2017-12-27 23:51:43*


## gitlab4j-api-4.7.6
### GitHub [#110](https://github.com/gmessner/gitlab4j-api/pull/110)  Added support to list MergeRequests by state  

**Added support to list MergeRequests by state (#110)**

 * Added support to list MergeRequests by state
 * Added getMergeRequests by State with paging support

[c292eb754f3441c](https://github.com/gmessner/gitlab4j-api/commit/c292eb754f3441c) Jeroen Wijdemans *2017-12-17 20:19:06*


### No issue

**Consolidated method calls.**


[e0eb2cce43d6ac1](https://github.com/gmessner/gitlab4j-api/commit/e0eb2cce43d6ac1) Greg Messner *2017-12-17 20:42:26*

**Updated for 4.7.6**


[067a73d99b15f87](https://github.com/gmessner/gitlab4j-api/commit/067a73d99b15f87) Greg Messner *2017-12-17 20:42:03*


## gitlab4j-api-4.7.5
### GitHub [#109](https://github.com/gmessner/gitlab4j-api/issues/109) addGroup doesn&#39;t return Group    *bug*  *enhancement*  

**Updated for release 4.7.5 (#109).**


[6252ac35b51d3e4](https://github.com/gmessner/gitlab4j-api/commit/6252ac35b51d3e4) Greg Messner *2017-12-12 03:03:24*

**addGroup() methods now return the created Group instance (#109).**


[3cbf13d2032e7bc](https://github.com/gmessner/gitlab4j-api/commit/3cbf13d2032e7bc) Greg Messner *2017-12-12 03:02:14*


## gitlab4j-api-4.7.4
### GitHub [#103](https://github.com/gmessner/gitlab4j-api/issues/103) How to change AccessLevel of existing project member?    *enhancement*  

**Updated for release 4.7.4 (#103).**


[e2f729c6a3198b2](https://github.com/gmessner/gitlab4j-api/commit/e2f729c6a3198b2) Greg Messner *2017-12-07 18:03:06*

**Added updateMember() methods (#103).**


[f6965d3373c75d3](https://github.com/gmessner/gitlab4j-api/commit/f6965d3373c75d3) Greg Messner *2017-12-07 18:01:57*


### GitHub [#108](https://github.com/gmessner/gitlab4j-api/pull/108) &quot;ref_name&quot; was changed to &quot;ref&quot; in RepositoryAPI v4  

**"ref_name" was changed to "ref" in APIv4 (#108)**

 * https://gitlab.com/gitlab-org/gitlab-ce/blob/8-16-stable/doc/api/repositories.md - optional &quot;ref_name&quot; for V3
 * https://docs.gitlab.com/ce/api/repositories.html - optional &quot;ref&quot; for V4

[2aa68d2f3538503](https://github.com/gmessner/gitlab4j-api/commit/2aa68d2f3538503) khvalIvan *2017-12-06 15:25:02*


### No issue

**Made note about removal of login bold.**


[d6d6ba160b98cb5](https://github.com/gmessner/gitlab4j-api/commit/d6d6ba160b98cb5) Greg Messner *2017-12-06 02:45:42*


## gitlab4j-api-4.7.3
### GitHub [#104](https://github.com/gmessner/gitlab4j-api/issues/104) Parsing &quot;mo&quot; time duration is not supported    *bug*  

**Added support for month (mo) durations (#104).**


[b863c786ba4e0d3](https://github.com/gmessner/gitlab4j-api/commit/b863c786ba4e0d3) Greg Messner *2017-12-05 03:40:01*


### GitHub [#106](https://github.com/gmessner/gitlab4j-api/issues/106) Return project on successful fork    *enhancement*  

**Added @return to forkProject() methods (#106).**


[60879e8e738f8d9](https://github.com/gmessner/gitlab4j-api/commit/60879e8e738f8d9) Greg Messner *2017-12-05 03:43:57*

**forkProject() methods now return the newly forked Project instance (#106).**


[463c6e96a7a4006](https://github.com/gmessner/gitlab4j-api/commit/463c6e96a7a4006) Greg Messner *2017-12-05 03:41:06*


### No issue

**Updated for release 4.7.3**


[34ba7e1915b8904](https://github.com/gmessner/gitlab4j-api/commit/34ba7e1915b8904) Greg Messner *2017-12-05 03:42:04*


## gitlab4j-api-4.7.2
### GitHub [#105](https://github.com/gmessner/gitlab4j-api/pull/105)  Complete the javadoc of NoteApi and LabelApi  

**Complete the javadoc of NoteApi and LabelApi (#105)**

 * Complete the javadoc of NoteApi and LabelApi
 * Fix createLabel and updateIssueNote

[5e7248d67f1b07b](https://github.com/gmessner/gitlab4j-api/commit/5e7248d67f1b07b) Stel000 *2017-12-04 15:55:21*


### GitHub [#107](https://github.com/gmessner/gitlab4j-api/issues/107) Fork throws exception on success    *bug*  

**forkProject() now looks for CREATED status (#107).**


[dce1deb9ed9daa8](https://github.com/gmessner/gitlab4j-api/commit/dce1deb9ed9daa8) Greg Messner *2017-12-04 16:24:17*


### No issue

**Added comments about login not being supported in version 10.2.0+**


[5e5e8b345e83f33](https://github.com/gmessner/gitlab4j-api/commit/5e5e8b345e83f33) Greg Messner *2017-12-04 16:32:50*

**Updated for 4.7.2.**


[b6e06006c6a6cec](https://github.com/gmessner/gitlab4j-api/commit/b6e06006c6a6cec) Greg Messner *2017-12-04 16:32:11*

**Eclipse sorted imports differently than PR, changing file.**


[5a0909debe09099](https://github.com/gmessner/gitlab4j-api/commit/5a0909debe09099) Greg Messner *2017-12-04 16:26:28*

**Now skips session tests if version is >= 10.2**


[f917e9f8ec91285](https://github.com/gmessner/gitlab4j-api/commit/f917e9f8ec91285) Greg Messner *2017-12-04 16:25:18*


## gitlab4j-api-4.7.1
### GitHub [#102](https://github.com/gmessner/gitlab4j-api/pull/102) Allow to setup client properties  

**Allow to setup client properties (#102)**


[cf7164e7d5bd020](https://github.com/gmessner/gitlab4j-api/commit/cf7164e7d5bd020) Fedor Bobin *2017-11-30 06:49:59*


### No issue

**Updated for 4.7.1**


[feef48716d69099](https://github.com/gmessner/gitlab4j-api/commit/feef48716d69099) Greg Messner *2017-11-30 14:07:16*

**Fixed Label entity and added unit test for same.**


[284b11e40b59de2](https://github.com/gmessner/gitlab4j-api/commit/284b11e40b59de2) Greg Messner *2017-11-30 14:03:41*

**Updated for 4.7.0**


[cdf53b786f63a4e](https://github.com/gmessner/gitlab4j-api/commit/cdf53b786f63a4e) Greg Messner *2017-11-30 13:53:15*


## gitlab4j-api-4.7.0
### GitHub [#100](https://github.com/gmessner/gitlab4j-api/pull/100) Create User API has some problems, and I modified it  

**Create User API has some problems, and I modified it (#100)**


[3a9bd74a76ca2c4](https://github.com/gmessner/gitlab4j-api/commit/3a9bd74a76ca2c4) menduo *2017-11-30 05:08:07*


### GitHub [#101](https://github.com/gmessner/gitlab4j-api/issues/101) protectBranch method throws GitLabApiException: Entity must not be null for http method PUT    *bug*  

**Fixed PUT with no entity (#101).**


[133623a0fbc874d](https://github.com/gmessner/gitlab4j-api/commit/133623a0fbc874d) Greg Messner *2017-11-30 04:47:05*

**Added tests for protectBranch() and unprotectBranch() (#101)**


[0e33be2d4dfb7a6](https://github.com/gmessner/gitlab4j-api/commit/0e33be2d4dfb7a6) Greg Messner *2017-11-30 04:46:02*


### GitHub [#96](https://github.com/gmessner/gitlab4j-api/pull/96) Add a way to get only one member of a group  

**Add a way to get only one member of a group (#96)**


[7cd9f2626ce2973](https://github.com/gmessner/gitlab4j-api/commit/7cd9f2626ce2973) Alexandre Vanhecke *2017-11-26 03:42:17*


### GitHub [#98](https://github.com/gmessner/gitlab4j-api/issues/98) ForkAPI missing?    *enhancement*  

**Added fork project support (#98).**


[158abc0f5455b09](https://github.com/gmessner/gitlab4j-api/commit/158abc0f5455b09) Greg Messner *2017-11-30 05:06:59*


### GitHub [#99](https://github.com/gmessner/gitlab4j-api/pull/99) Add project milestones,issue notes and labels API.  

**Add project milestones,issue notes and labels API. (#99)**

 * [Add]Add gitlab project milestone api.
 * [Add]Check projectId not null.
 * [Fix]Fix some bugs.
 * [Add]Add gitlab issue notes api.
 * [Add]Add gitlab labels api.
 * [Imp]Add attr-required to Param.
 * [Fix]Fix test error.

[0aa2b5899eebc1f](https://github.com/gmessner/gitlab4j-api/commit/0aa2b5899eebc1f) Stel000 *2017-11-30 05:15:50*


### No issue

**Misc code clean-up.**


[a85c0ec1278dea7](https://github.com/gmessner/gitlab4j-api/commit/a85c0ec1278dea7) Greg Messner *2017-11-30 06:39:08*

**Renamed MilestonesApi.**


[6456679dbff313f](https://github.com/gmessner/gitlab4j-api/commit/6456679dbff313f) Greg Messner *2017-11-30 06:38:42*

**Fixed javadocs on deleteBranch().**


[ed46ded63a2f2f7](https://github.com/gmessner/gitlab4j-api/commit/ed46ded63a2f2f7) Greg Messner *2017-11-30 05:06:20*

**Corrected example for NotesApi.**


[837e1ab3ab235ce](https://github.com/gmessner/gitlab4j-api/commit/837e1ab3ab235ce) Greg Messner *2017-11-27 01:15:16*


## gitlab4j-api-4.6.9
### GitHub [#94](https://github.com/gmessner/gitlab4j-api/issues/94) Invalid certificate error in login api    *enhancement*  

**Added login() method with an ignoreCertificateErrors parameter (#94).**


[65e0da8369afaac](https://github.com/gmessner/gitlab4j-api/commit/65e0da8369afaac) Greg Messner *2017-11-06 15:56:06*


### No issue

**Updated for release 4.6.9**


[5b4a2e5b5fe7725](https://github.com/gmessner/gitlab4j-api/commit/5b4a2e5b5fe7725) Greg Messner *2017-11-06 15:58:03*


## gitlab4j-api-4.6.8
### GitHub [#91](https://github.com/gmessner/gitlab4j-api/issues/91) impersonation tokens  

**Changed deleteImpersonationToken() to revokeImpersonationToken() (#91).**


[27f783cab2530e8](https://github.com/gmessner/gitlab4j-api/commit/27f783cab2530e8) Greg Messner *2017-11-05 15:40:33*

**Added support for impersonation tokens (#91).**


[7c184f7f03677d1](https://github.com/gmessner/gitlab4j-api/commit/7c184f7f03677d1) Greg Messner *2017-11-05 15:22:45*

**Added tests for impersonation tokens (#91).**


[e48dace92ae6bd7](https://github.com/gmessner/gitlab4j-api/commit/e48dace92ae6bd7) Greg Messner *2017-11-05 15:21:31*

**Added test for ImpersonationToken (#91).**


[f4546228c551fd0](https://github.com/gmessner/gitlab4j-api/commit/f4546228c551fd0) Greg Messner *2017-11-05 14:23:08*

**Initial check-in (#91).**


[24d2f1feac03802](https://github.com/gmessner/gitlab4j-api/commit/24d2f1feac03802) Greg Messner *2017-11-05 14:22:24*


### No issue

**Updated for release 4.6.8**


[dfad2d807ab83a6](https://github.com/gmessner/gitlab4j-api/commit/dfad2d807ab83a6) Greg Messner *2017-11-05 15:39:54*

**Cleaned up imports.**


[76416755389e553](https://github.com/gmessner/gitlab4j-api/commit/76416755389e553) Greg Messner *2017-11-05 15:30:46*

**Updated for 4.6.7**


[ec0542714683a89](https://github.com/gmessner/gitlab4j-api/commit/ec0542714683a89) Greg Messner *2017-11-05 15:24:21*


## gitlab4j-api-4.6.6
### GitHub [#92](https://github.com/gmessner/gitlab4j-api/issues/92) sudo    *enhancement*  

**Added info on TEST_SUDO_AS_USERNAME (#92).**


[5b878d7c360e80d](https://github.com/gmessner/gitlab4j-api/commit/5b878d7c360e80d) Greg Messner *2017-11-04 19:52:55*

**Mods to support sudo (#92).**


[96d0fb2ed951cd9](https://github.com/gmessner/gitlab4j-api/commit/96d0fb2ed951cd9) Greg Messner *2017-11-04 19:50:58*


### No issue

**Add docs for login() and sudo().**


[797ea6bfebfde47](https://github.com/gmessner/gitlab4j-api/commit/797ea6bfebfde47) Greg Messner *2017-11-04 20:06:06*

**Updated for release 4.6.5.**


[1d025522072555a](https://github.com/gmessner/gitlab4j-api/commit/1d025522072555a) Greg Messner *2017-10-29 16:31:27*


## gitlab4j-api-4.6.5
### GitHub [#88](https://github.com/gmessner/gitlab4j-api/issues/88) Getting exception when trying to get tree of a specific project.  

**Added 'commit' TreeItem.Type (#88).**


[9215abdf695349d](https://github.com/gmessner/gitlab4j-api/commit/9215abdf695349d) Greg Messner *2017-10-29 07:23:54*


### GitHub [#89](https://github.com/gmessner/gitlab4j-api/pull/89) Get active/blocked users  

**Get active/blocked users (#89)**


[1e50236afa63237](https://github.com/gmessner/gitlab4j-api/commit/1e50236afa63237) Alex Bobkov *2017-10-29 06:54:13*


### No issue

**Eclipse reordering of imports.**


[e5dc32c40f6e22d](https://github.com/gmessner/gitlab4j-api/commit/e5dc32c40f6e22d) Greg Messner *2017-10-29 07:24:52*


## gitlab4j-api-4.6.4
### GitHub [#87](https://github.com/gmessner/gitlab4j-api/pull/87) Get project users  

**Get project users (#87)**

 * Get project users (not members)
 * https://docs.gitlab.com/ce/api/projects.html#get-project-users
 * Removed wildcard imports

[d3508619d0196d6](https://github.com/gmessner/gitlab4j-api/commit/d3508619d0196d6) Alex *2017-10-27 04:55:09*


### No issue

**Updated for 4.6.4.**


[007f6c14106d54f](https://github.com/gmessner/gitlab4j-api/commit/007f6c14106d54f) Greg Messner *2017-10-27 06:02:29*

**Added getProjectUsers() with search.**


[8be77e82ab14aa9](https://github.com/gmessner/gitlab4j-api/commit/8be77e82ab14aa9) Greg Messner *2017-10-27 06:01:15*

**Added test for ProjectUser.**


[97884fc7b1b04af](https://github.com/gmessner/gitlab4j-api/commit/97884fc7b1b04af) Greg Messner *2017-10-27 06:00:42*

**Initial check-in.**


[f66b4d2f05ead74](https://github.com/gmessner/gitlab4j-api/commit/f66b4d2f05ead74) Greg Messner *2017-10-27 06:00:02*

**Updated for release 4.6.3.**


[56306fcfa42a53f](https://github.com/gmessner/gitlab4j-api/commit/56306fcfa42a53f) Greg Messner *2017-10-23 18:17:55*


## gitlab4j-api-4.6.3
### No issue

**Added tests for w (weeks) in duration.**


[0b2d59dd096071c](https://github.com/gmessner/gitlab4j-api/commit/0b2d59dd096071c) Greg Messner *2017-10-23 18:02:03*

**Added support for w (week) in duration.**


[a2514940962bb8c](https://github.com/gmessner/gitlab4j-api/commit/a2514940962bb8c) Greg Messner *2017-10-23 18:01:27*

**Updated for release 4.6.2**


[c4dd092bbd51274](https://github.com/gmessner/gitlab4j-api/commit/c4dd092bbd51274) Greg Messner *2017-10-11 15:41:30*


## gitlab4j-api-4.6.2
### GitHub [#82](https://github.com/gmessner/gitlab4j-api/issues/82) RepositoryFileApi.updateFile V3 expected response code should be OK  

**Fixed createFile() for V3 API (#82).**


[c1473753b3b0720](https://github.com/gmessner/gitlab4j-api/commit/c1473753b3b0720) Greg Messner *2017-10-09 14:46:44*


### GitHub [#83](https://github.com/gmessner/gitlab4j-api/pull/83) Add getTrace to JobApi  

**Add getTrace to JobApi (#83)**


[0d9ad6fa49d266e](https://github.com/gmessner/gitlab4j-api/commit/0d9ad6fa49d266e) fgro93 *2017-10-11 01:29:05*


### No issue

**Fixed test for createFile() and deleteFile().**


[f60ee55a548dfad](https://github.com/gmessner/gitlab4j-api/commit/f60ee55a548dfad) Greg Messner *2017-10-11 04:29:36*

**Added test for createFile() and deleteFile().**


[44837b9a0681b97](https://github.com/gmessner/gitlab4j-api/commit/44837b9a0681b97) Greg Messner *2017-10-11 04:18:15*

**Updated for release 4.6.1.**


[a65eeeaa3b61758](https://github.com/gmessner/gitlab4j-api/commit/a65eeeaa3b61758) Greg Messner *2017-10-05 17:28:56*


## gitlab4j-api-4.6.1
### GitHub [#82](https://github.com/gmessner/gitlab4j-api/issues/82) RepositoryFileApi.updateFile V3 expected response code should be OK  

**Fixed V3 API response codes (#82)**


[1e15337134de4f2](https://github.com/gmessner/gitlab4j-api/commit/1e15337134de4f2) Greg Messner *2017-09-29 05:24:11*


### No issue

**Removed JAR download link.**


[4e9c6517bdadc08](https://github.com/gmessner/gitlab4j-api/commit/4e9c6517bdadc08) Greg Messner *2017-09-28 00:20:17*

**Updated copyright.**


[c6515f9424c96d7](https://github.com/gmessner/gitlab4j-api/commit/c6515f9424c96d7) Greg Messner *2017-09-27 05:56:35*

**Changed Javadocs link.**


[bb6f578ae5cd649](https://github.com/gmessner/gitlab4j-api/commit/bb6f578ae5cd649) Greg Messner *2017-09-24 23:28:05*


## gitlab4j-api-4.6.0
### GitHub [#78](https://github.com/gmessner/gitlab4j-api/issues/78) getMergeRequest Creates an invalid url.  

**Fixed incorrect URLs (#78)**


[87931a4d2402e8c](https://github.com/gmessner/gitlab4j-api/commit/87931a4d2402e8c) Greg Messner *2017-09-24 05:29:43*


### GitHub [#79](https://github.com/gmessner/gitlab4j-api/issues/79) OAuth2 tokens support  

**Mod to fix ignoreCertyificateErrors and add access_token support (#79, #80)**


[b7e5ba3e7cc201c](https://github.com/gmessner/gitlab4j-api/commit/b7e5ba3e7cc201c) Greg Messner *2017-09-24 21:45:17*

**Added TokenType enum (#79)**


[2f775051b1b8cbb](https://github.com/gmessner/gitlab4j-api/commit/2f775051b1b8cbb) Greg Messner *2017-09-24 21:39:16*

**Initial check-in (#79)**


[d4f32c10674369d](https://github.com/gmessner/gitlab4j-api/commit/d4f32c10674369d) Greg Messner *2017-09-24 21:38:33*


### GitHub [#80](https://github.com/gmessner/gitlab4j-api/issues/80) Cannot connect to Gitlab due to cert validation even though it&#39;s set to ignore  

**Mod to fix ignoreCertyificateErrors and add access_token support (#79, #80)**


[b7e5ba3e7cc201c](https://github.com/gmessner/gitlab4j-api/commit/b7e5ba3e7cc201c) Greg Messner *2017-09-24 21:45:17*

**Initial check-in (#80)**


[964c62a6a762df5](https://github.com/gmessner/gitlab4j-api/commit/964c62a6a762df5) Greg Messner *2017-09-24 21:37:55*


### GitHub [#81](https://github.com/gmessner/gitlab4j-api/pull/81) Add GitLab&#39;s (very limited) time tracking API  

**Add GitLab's (very limited) time tracking API (#81)**


[978c6380dca55a4](https://github.com/gmessner/gitlab4j-api/commit/978c6380dca55a4) Schakko *2017-09-23 20:46:11*


### No issue

**Updated for version 4.6.0**


[ae683d68e1286d6](https://github.com/gmessner/gitlab4j-api/commit/ae683d68e1286d6) Greg Messner *2017-09-24 21:48:08*

**Bumped version to 4.6.0 because of changes to ignoreCertificateErrors and access_token support.**


[91516fb0b894816](https://github.com/gmessner/gitlab4j-api/commit/91516fb0b894816) Greg Messner *2017-09-24 21:47:13*

**Reset version back to 4.5.1-SNAPSHOT.**


[e9a33bb47e9f683](https://github.com/gmessner/gitlab4j-api/commit/e9a33bb47e9f683) Greg Messner *2017-09-24 05:30:45*

**Fixed expected responses for duration related methods.**


[b3586980aceec83](https://github.com/gmessner/gitlab4j-api/commit/b3586980aceec83) Greg Messner *2017-09-24 05:28:50*

**Added overloaded time duration related methods.**


[b1f9763ef43daa3](https://github.com/gmessner/gitlab4j-api/commit/b1f9763ef43daa3) Greg Messner *2017-09-24 04:57:08*

**Modified to use new Duration class.**


[fd0b882595c4505](https://github.com/gmessner/gitlab4j-api/commit/fd0b882595c4505) Greg Messner *2017-09-24 04:56:36*

**Initial check-in.**


[32b31ca009fc2d0](https://github.com/gmessner/gitlab4j-api/commit/32b31ca009fc2d0) Greg Messner *2017-09-24 04:55:15*


## gitlab4j-api-4.5.0
### GitHub [#69](https://github.com/gmessner/gitlab4j-api/issues/69) Is there a way to submit an issue through the API?   

**Mods to support IssuesApi (#69)**


[a8cd89f91af46fb](https://github.com/gmessner/gitlab4j-api/commit/a8cd89f91af46fb) Greg Messner *2017-09-18 06:53:13*

**Mods to support IssuesApi (#69)**


[222fb26d7efd9b2](https://github.com/gmessner/gitlab4j-api/commit/222fb26d7efd9b2) Greg Messner *2017-09-18 06:51:30*

**Initial check-in (#69)**


[0ebaceea0c1596b](https://github.com/gmessner/gitlab4j-api/commit/0ebaceea0c1596b) Greg Messner *2017-09-18 06:50:43*


### GitHub [#70](https://github.com/gmessner/gitlab4j-api/issues/70) deploy keys  

**Initial check-in (#70)**


[73f3502f9c83638](https://github.com/gmessner/gitlab4j-api/commit/73f3502f9c83638) Greg Messner *2017-09-18 06:49:52*

**Fixed Javadoc issues (#70)**


[45dc78b1c6d05f5](https://github.com/gmessner/gitlab4j-api/commit/45dc78b1c6d05f5) Greg Messner *2017-09-17 17:39:11*

**Initial check-in (#70)**


[d4781bbf4421400](https://github.com/gmessner/gitlab4j-api/commit/d4781bbf4421400) Greg Messner *2017-09-17 17:34:44*


### GitHub [#73](https://github.com/gmessner/gitlab4j-api/pull/73) Added get commits by path method  

**Added get commits by path method (#73)**

 * Added get commits by path method

[ec39b57374b4d46](https://github.com/gmessner/gitlab4j-api/commit/ec39b57374b4d46) orangeStas *2017-09-16 05:08:32*


### No issue

**Fixed DeployKeysApi sample.**


[5c23b1749d16854](https://github.com/gmessner/gitlab4j-api/commit/5c23b1749d16854) Greg Messner *2017-09-18 06:55:06*

**Fixed compile warning.**


[7abd37b467c82f6](https://github.com/gmessner/gitlab4j-api/commit/7abd37b467c82f6) Greg Messner *2017-09-18 06:52:42*

**Added DeployKeysApi and IssuesApi info.**


[a7e93a10641fe31](https://github.com/gmessner/gitlab4j-api/commit/a7e93a10641fe31) Greg Messner *2017-09-18 06:52:10*

**General cleanup.**


[6c3e3a3cefd44ce](https://github.com/gmessner/gitlab4j-api/commit/6c3e3a3cefd44ce) Greg Messner *2017-09-17 22:56:07*

**Udated Jackson and Jersey verions.**


[9f6022dc2378ed9](https://github.com/gmessner/gitlab4j-api/commit/9f6022dc2378ed9) Greg Messner *2017-09-17 22:55:36*

**Removed unused import.**


[ca72221b94bf8e6](https://github.com/gmessner/gitlab4j-api/commit/ca72221b94bf8e6) Greg Messner *2017-09-17 18:59:48*

**Added more get commits by path tests.**


[b46754c4be1bb64](https://github.com/gmessner/gitlab4j-api/commit/b46754c4be1bb64) Greg Messner *2017-09-17 18:57:53*

**Added getCommits(int projectId, String ref, Date since, Date until, String path).**


[7ea980e5fda5720](https://github.com/gmessner/gitlab4j-api/commit/7ea980e5fda5720) Greg Messner *2017-09-17 18:33:39*

**Added support for deploy keys.**


[97d63f17eda0d7f](https://github.com/gmessner/gitlab4j-api/commit/97d63f17eda0d7f) Greg Messner *2017-09-17 17:33:23*

**Bumped version to 4.5.0**


[4c9df1dbc75913c](https://github.com/gmessner/gitlab4j-api/commit/4c9df1dbc75913c) Greg Messner *2017-09-17 17:32:32*


## gitlab4j-api-4.4.8
### No issue

**Fixd getGroup(String groupPath).**


[34a3a12fdee7288](https://github.com/gmessner/gitlab4j-api/commit/34a3a12fdee7288) Greg Messner *2017-08-28 00:25:48*

**Added test for snippet content.**


[117e13a0896670b](https://github.com/gmessner/gitlab4j-api/commit/117e13a0896670b) Greg Messner *2017-08-28 00:17:48*

**Fixed javadoc problems related to project snippets.**


[8caa0a93b60742a](https://github.com/gmessner/gitlab4j-api/commit/8caa0a93b60742a) Greg Messner *2017-08-28 00:06:12*

**Added support for project snippets.**


[63ee9bf827c74cb](https://github.com/gmessner/gitlab4j-api/commit/63ee9bf827c74cb) Greg Messner *2017-08-28 00:00:59*


## gitlab4j-api-4.4.7
### GitHub [#67](https://github.com/gmessner/gitlab4j-api/issues/67) API to get comments of a commit is missing  

**Added tests for getComments() and addComment() (#67).**


[9f480b3c2d748dd](https://github.com/gmessner/gitlab4j-api/commit/9f480b3c2d748dd) Greg Messner *2017-08-27 02:59:36*

**Added addComment(projectId, sha, note) (#67).**


[fb692b80fe24bf2](https://github.com/gmessner/gitlab4j-api/commit/fb692b80fe24bf2) Greg Messner *2017-08-27 02:58:54*

**Chaged response status to CREATED for addComment() (#67).**


[92afc5fab96a3b5](https://github.com/gmessner/gitlab4j-api/commit/92afc5fab96a3b5) Greg Messner *2017-08-27 02:31:58*

**Added Comment support to the CommitsApi (#67).**


[7280af746a40af3](https://github.com/gmessner/gitlab4j-api/commit/7280af746a40af3) Greg Messner *2017-08-27 02:30:27*


### GitHub [#68](https://github.com/gmessner/gitlab4j-api/issues/68) API to get commits of a merge request is missing  

**Added getCommits() methods for merge requests (#68).**


[14519fae2517469](https://github.com/gmessner/gitlab4j-api/commit/14519fae2517469) Greg Messner *2017-08-27 03:17:15*


### No issue

**Updated for 4.4.7**


[fc847c60a97dc37](https://github.com/gmessner/gitlab4j-api/commit/fc847c60a97dc37) Greg Messner *2017-08-27 04:57:59*

**Added support for V3 API end points.**


[06789bd044c4caf](https://github.com/gmessner/gitlab4j-api/commit/06789bd044c4caf) Greg Messner *2017-08-27 04:56:06*

**Cleaned up imports.**


[2c76c952749d263](https://github.com/gmessner/gitlab4j-api/commit/2c76c952749d263) Greg Messner *2017-08-27 04:21:58*


## gitlab4j-api-4.4.6
### No issue

**Fixed getBranch() when branch name was not URL safe.**


[f95ec6f076ed8ba](https://github.com/gmessner/gitlab4j-api/commit/f95ec6f076ed8ba) Greg Messner *2017-08-21 05:05:34*

**Updated for 4.4.6**


[411e060ad3dc959](https://github.com/gmessner/gitlab4j-api/commit/411e060ad3dc959) Greg Messner *2017-08-21 04:29:49*

**Added support for merge request approvals.**


[8b7596168d5eea4](https://github.com/gmessner/gitlab4j-api/commit/8b7596168d5eea4) Greg Messner *2017-08-21 04:28:34*

**Added methods to get and delete a single project issue.**


[133e6e17b7871ab](https://github.com/gmessner/gitlab4j-api/commit/133e6e17b7871ab) Greg Messner *2017-08-21 04:24:55*

**Added getUser(String username) and test for same.**


[67043ccec0ad312](https://github.com/gmessner/gitlab4j-api/commit/67043ccec0ad312) Greg Messner *2017-08-21 04:23:50*

**Initial heck-in.**


[376d5ac1f2caa65](https://github.com/gmessner/gitlab4j-api/commit/376d5ac1f2caa65) Greg Messner *2017-08-21 04:23:27*

**Added links to sub APIs.**


[9cac3b04dc37222](https://github.com/gmessner/gitlab4j-api/commit/9cac3b04dc37222) Greg Messner *2017-08-13 18:48:22*

**Added EventsApi info.**


[83184f997229324](https://github.com/gmessner/gitlab4j-api/commit/83184f997229324) Greg Messner *2017-08-13 05:24:08*


## gitlab4j-api-4.4.5
### GitHub [#60](https://github.com/gmessner/gitlab4j-api/issues/60) Events API?  

**Added test for fetching events with dates (#60).**


[e28df48715e7c3f](https://github.com/gmessner/gitlab4j-api/commit/e28df48715e7c3f) Greg Messner *2017-08-13 04:35:32*

**Mods to support EventsApi (#60).**


[13afdbb0030b58b](https://github.com/gmessner/gitlab4j-api/commit/13afdbb0030b58b) Greg Messner *2017-08-13 04:17:23*

**Initial check-in (#60).**


[c50c87cda94b6bf](https://github.com/gmessner/gitlab4j-api/commit/c50c87cda94b6bf) Greg Messner *2017-08-13 04:16:45*


### No issue

**Updated for release 4.4.5**


[194c3719c6edc5b](https://github.com/gmessner/gitlab4j-api/commit/194c3719c6edc5b) Greg Messner *2017-08-13 04:23:41*

**Fixed Javadoc warning.**


[55c73a83b15accf](https://github.com/gmessner/gitlab4j-api/commit/55c73a83b15accf) Greg Messner *2017-08-13 04:22:34*


## gitlab4j-api-4.4.4
### No issue

**Added getCurrentUser().**


[953ac4af888fb8e](https://github.com/gmessner/gitlab4j-api/commit/953ac4af888fb8e) Greg Messner *2017-08-09 05:49:30*

**Initial check-in.**


[2e2c67fc9ad43a4](https://github.com/gmessner/gitlab4j-api/commit/2e2c67fc9ad43a4) Greg Messner *2017-08-09 05:49:08*

**Updated for 4.4.4**


[eb829861ec7bf87](https://github.com/gmessner/gitlab4j-api/commit/eb829861ec7bf87) Greg Messner *2017-08-09 05:48:35*


## gitlab4j-api-4.4.3
### GitHub [#55](https://github.com/gmessner/gitlab4j-api/pull/55) getGroup overloaded to support path argument  

**getGroup overloaded to support path argument (#55)**


[e970cfae12ce640](https://github.com/gmessner/gitlab4j-api/commit/e970cfae12ce640) Denny Ayard *2017-08-01 18:10:56*


### No issue

**Mods to support acceptMergeRequest().**


[4e9d14427853c80](https://github.com/gmessner/gitlab4j-api/commit/4e9d14427853c80) Greg Messner *2017-08-06 01:10:38*

**Mods to support updateProject().**


[aea794327cb2b39](https://github.com/gmessner/gitlab4j-api/commit/aea794327cb2b39) Greg Messner *2017-08-06 01:08:59*

**Updated for 4.4.3**


[99c43d2e7d4beb4](https://github.com/gmessner/gitlab4j-api/commit/99c43d2e7d4beb4) Greg Messner *2017-08-06 01:07:27*


## gitlab4j-api-4.4.2
### No issue

**Updated for version 4.4.2**


[b0fd28e396c9dae](https://github.com/gmessner/gitlab4j-api/commit/b0fd28e396c9dae) Greg Messner *2017-07-27 03:25:04*

**Fixed login() with API version and exposed setIgnoreCerificateErrors().**


[808a9a93977b995](https://github.com/gmessner/gitlab4j-api/commit/808a9a93977b995) Greg Messner *2017-07-27 03:24:17*

**Added test for V3 login.**


[19df544fbc2c0c7](https://github.com/gmessner/gitlab4j-api/commit/19df544fbc2c0c7) Greg Messner *2017-07-27 03:23:20*

**Fixed getCommits() example.**


[d260ce9b4e054db](https://github.com/gmessner/gitlab4j-api/commit/d260ce9b4e054db) Greg Messner *2017-07-26 18:48:03*

**Added link to Javadocs.**


[44df0ed8a8b4a08](https://github.com/gmessner/gitlab4j-api/commit/44df0ed8a8b4a08) Greg Messner *2017-07-16 20:02:56*

**Added a header above the Sub API examples.**


[9448b887f893fb5](https://github.com/gmessner/gitlab4j-api/commit/9448b887f893fb5) Greg Messner *2017-07-15 21:28:24*

**Cleaned up Pager example.**


[3912beee0949527](https://github.com/gmessner/gitlab4j-api/commit/3912beee0949527) Greg Messner *2017-07-15 21:25:10*

**Fixed typo in Pager example.**


[1c97d51e1692cbf](https://github.com/gmessner/gitlab4j-api/commit/1c97d51e1692cbf) Greg Messner *2017-07-15 18:18:59*

**Fixed Pager examples.**


[eb9eb7c7ee3fe86](https://github.com/gmessner/gitlab4j-api/commit/eb9eb7c7ee3fe86) Greg Messner *2017-07-15 17:53:45*


## gitlab4j-api-4.4.1
### No issue

**Updated for 4.4.1**


[6980881f9310f83](https://github.com/gmessner/gitlab4j-api/commit/6980881f9310f83) Greg Messner *2017-07-12 04:51:43*

**Added getCommits() with since and until parameters.**


[aab4aead09f8f38](https://github.com/gmessner/gitlab4j-api/commit/aab4aead09f8f38) Greg Messner *2017-07-12 04:50:42*


## gitlab4j-api-4.4.0
### GitHub [#45](https://github.com/gmessner/gitlab4j-api/pull/45) Add Job API  

**Add Job API (#45)**


[14ef9b762ddf22e](https://github.com/gmessner/gitlab4j-api/commit/14ef9b762ddf22e) Chen Chenglong *2017-07-11 03:32:01*


### No issue

**Finalized Job API support.**


[a635acf6fbd5dd3](https://github.com/gmessner/gitlab4j-api/commit/a635acf6fbd5dd3) Greg Messner *2017-07-11 04:59:03*

**Bumped version to 4.4.0 for next release.**


[280fd2ec5bff4b4](https://github.com/gmessner/gitlab4j-api/commit/280fd2ec5bff4b4) Greg Messner *2017-07-11 04:58:23*

**Added JobAPi and updated for version 4.4.0**


[d3041ae2a08f258](https://github.com/gmessner/gitlab4j-api/commit/d3041ae2a08f258) Greg Messner *2017-07-11 04:57:32*

**Initial check-in.**


[209b616d70adbb8](https://github.com/gmessner/gitlab4j-api/commit/209b616d70adbb8) Greg Messner *2017-07-11 04:53:08*


## gitlab4j-api-4.3.6
### GitHub [#37](https://github.com/gmessner/gitlab4j-api/issues/37) no support SSH key  

**Added SSH key support to UserApi (#37).**


[7d0a4a899ba066b](https://github.com/gmessner/gitlab4j-api/commit/7d0a4a899ba066b) Greg Messner *2017-06-30 14:38:04*


### No issue

**Updated for 4.3.6**


[dd724774ab465e6](https://github.com/gmessner/gitlab4j-api/commit/dd724774ab465e6) Greg Messner *2017-07-06 06:57:54*

**Fixed URLs by adding the URL encoded filepath to the URL.**


[92e79860a0fae2c](https://github.com/gmessner/gitlab4j-api/commit/92e79860a0fae2c) Greg Messner *2017-07-06 06:55:51*

**Cleaned up Javadocs and renamed getIssues() to getNotes().**


[820ab246f6ca620](https://github.com/gmessner/gitlab4j-api/commit/820ab246f6ca620) Greg Messner *2017-07-06 06:53:41*

**Initial implementation of NotesApi for fetching notes of an issue**


[b2a4fc3c9c18dfd](https://github.com/gmessner/gitlab4j-api/commit/b2a4fc3c9c18dfd) jsimomaa *2017-07-06 06:21:18*

**Initial check-in.**


[ba4cd1f51c19120](https://github.com/gmessner/gitlab4j-api/commit/ba4cd1f51c19120) Greg Messner *2017-06-30 14:36:54*


## gitlab4j-api-4.3.5
### No issue

**Updated for 4.3.5**


[fcf87306a225de6](https://github.com/gmessner/gitlab4j-api/commit/fcf87306a225de6) Greg Messner *2017-06-28 04:57:35*

**Added support for last_activity_on and shared_runners_minutes_limit.**


[b5685c375ce4c55](https://github.com/gmessner/gitlab4j-api/commit/b5685c375ce4c55) Greg Messner *2017-06-28 04:56:46*


## gitlab4j-api-4.3.4
### GitHub [#38](https://github.com/gmessner/gitlab4j-api/issues/38) Compare branches, tags, or commits implementation? and CommitsApi.getDiff() problem!  

**Issue #38 - Fixed getDiff() and added compare().**


[3a3cbe8033b6c38](https://github.com/gmessner/gitlab4j-api/commit/3a3cbe8033b6c38) Greg Messner *2017-06-27 05:24:06*


### No issue

**Fixed Javadoc warning.**


[256c211e1d73fba](https://github.com/gmessner/gitlab4j-api/commit/256c211e1d73fba) Greg Messner *2017-06-27 05:26:58*

**Updated for 4.3.4**


[85d041772592e54](https://github.com/gmessner/gitlab4j-api/commit/85d041772592e54) Greg Messner *2017-06-27 05:25:07*


## gitlab4j-api-4.3.3
### No issue

**Now looks for the test-gitlab4j.properties in user.home.**


[c786505e96cc2c9](https://github.com/gmessner/gitlab4j-api/commit/c786505e96cc2c9) Greg Messner *2017-06-25 02:53:38*

**Fixed getStarredProjects() tests.**


[688be7af1df63be](https://github.com/gmessner/gitlab4j-api/commit/688be7af1df63be) Greg Messner *2017-06-25 01:32:38*

**Improved test coverage.**


[44d556bbf33656b](https://github.com/gmessner/gitlab4j-api/commit/44d556bbf33656b) Greg Messner *2017-06-25 01:07:50*

**Fixed findNamespace(String, int, int).**


[4f00311126d8e70](https://github.com/gmessner/gitlab4j-api/commit/4f00311126d8e70) Greg Messner *2017-06-25 01:07:14*

**Fixed Javadoc warning.**


[1a24aa335bddaa4](https://github.com/gmessner/gitlab4j-api/commit/1a24aa335bddaa4) Greg Messner *2017-06-25 00:18:49*

**Cleaned up imports.**


[7ab3af7241eb1ea](https://github.com/gmessner/gitlab4j-api/commit/7ab3af7241eb1ea) Greg Messner *2017-06-25 00:15:34*

**Updated for release 4.3.3**


[5a10a100a8a1c4c](https://github.com/gmessner/gitlab4j-api/commit/5a10a100a8a1c4c) Greg Messner *2017-06-25 00:11:38*

**Mods to properly downloading blobs and files.**


[048a8224f27ef25](https://github.com/gmessner/gitlab4j-api/commit/048a8224f27ef25) Greg Messner *2017-06-25 00:10:36*

**Set test coverage minimum to 60%.**


[03473020624123a](https://github.com/gmessner/gitlab4j-api/commit/03473020624123a) Greg Messner *2017-06-25 00:09:50*

**Improved test coverage and added test-gitlab4j.properties support.**


[fca0e76b3303002](https://github.com/gmessner/gitlab4j-api/commit/fca0e76b3303002) Greg Messner *2017-06-25 00:09:15*

**Improved test coverage and added test-gitlab4j.properties support.**


[08e77fd2c3071eb](https://github.com/gmessner/gitlab4j-api/commit/08e77fd2c3071eb) Greg Messner *2017-06-25 00:08:57*


## gitlab4j-api-4.3.2
### No issue

**Updated for 4.3.2**


[0a0a377d8f522ca](https://github.com/gmessner/gitlab4j-api/commit/0a0a377d8f522ca) Greg Messner *2017-06-22 07:37:44*

**Added created_at and updated_at fields.**


[bc9353ae18c693f](https://github.com/gmessner/gitlab4j-api/commit/bc9353ae18c693f) Greg Messner *2017-06-22 07:36:39*


## gitlab4j-api-4.3.1
### No issue

**Updated for release 4.3.1**


[f39db8ecbc8fd8a](https://github.com/gmessner/gitlab4j-api/commit/f39db8ecbc8fd8a) Greg Messner *2017-06-21 03:53:22*

**Added getVersion() API call.**


[a50caee0e292ddb](https://github.com/gmessner/gitlab4j-api/commit/a50caee0e292ddb) Greg Messner *2017-06-21 03:52:23*

**Initial check-in.**


[50c190b3ad64a37](https://github.com/gmessner/gitlab4j-api/commit/50c190b3ad64a37) Greg Messner *2017-06-21 03:51:53*

**Added horizontal rules between sections.**


[bb01542b6a95437](https://github.com/gmessner/gitlab4j-api/commit/bb01542b6a95437) Greg Messner *2017-06-19 05:10:56*


## gitlab4j-api-4.3.0
### No issue

**Adjexted jacoco settings.**


[837aec82b108425](https://github.com/gmessner/gitlab4j-api/commit/837aec82b108425) Greg Messner *2017-06-19 03:58:55*

**Set version to 4.3.0-SNAPSHOT.**


[76fb9fdd0206105](https://github.com/gmessner/gitlab4j-api/commit/76fb9fdd0206105) Greg Messner *2017-06-19 00:51:10*

**Added Pager<T> support.**


[0e2cd80d64e6256](https://github.com/gmessner/gitlab4j-api/commit/0e2cd80d64e6256) Greg Messner *2017-06-19 00:37:15*

**Initial check-in.**


[d5cebb3e09fd6b9](https://github.com/gmessner/gitlab4j-api/commit/d5cebb3e09fd6b9) Greg Messner *2017-06-19 00:36:15*


## gitlab4j-api-4.2.5
### No issue

**Updated for 4.2.5**


[fc08971d4f09ff2](https://github.com/gmessner/gitlab4j-api/commit/fc08971d4f09ff2) Greg Messner *2017-06-17 02:46:31*

**Updated getBranches() to set per_page to DEFAULT_PER_PAGE.**


[421c759198953c6](https://github.com/gmessner/gitlab4j-api/commit/421c759198953c6) Greg Messner *2017-06-16 14:43:50*

**Updated getCommits() to set per_page to DEFAULT_PER_PAGE.**


[2450d70d00a24b0](https://github.com/gmessner/gitlab4j-api/commit/2450d70d00a24b0) Greg Messner *2017-06-16 14:43:21*


## gitlab4j-api-4.2.4
### No issue

**Added getProjects(int page, int perPage).**


[f16e0e19c7238d5](https://github.com/gmessner/gitlab4j-api/commit/f16e0e19c7238d5) Greg Messner *2017-06-15 14:25:31*

**Updated for 4.2.4**


[97962d8e96a2998](https://github.com/gmessner/gitlab4j-api/commit/97962d8e96a2998) Greg Messner *2017-06-15 14:25:04*


## gitlab4j-api-4.2.3
### GitHub [#29](https://github.com/gmessner/gitlab4j-api/issues/29) ProjectApi removeMember() throws GitLabApiException at success case  

**Fixed issues related to HTTP status (#29, #31).**


[5373879f1167612](https://github.com/gmessner/gitlab4j-api/commit/5373879f1167612) Greg Messner *2017-06-14 14:43:07*


### GitHub [#30](https://github.com/gmessner/gitlab4j-api/issues/30) Search group is missing  

**Added getGroups(String search) #30.**


[302c6cf5c31d145](https://github.com/gmessner/gitlab4j-api/commit/302c6cf5c31d145) Greg Messner *2017-06-14 14:54:22*


### GitHub [#31](https://github.com/gmessner/gitlab4j-api/issues/31) GroupApi addMember() throws GitLabApiException: Created  

**Fixed issues related to HTTP status (#29, #31).**


[5373879f1167612](https://github.com/gmessner/gitlab4j-api/commit/5373879f1167612) Greg Messner *2017-06-14 14:43:07*


### No issue

**Updated for release 4.2.3**


[97e31a7472238e7](https://github.com/gmessner/gitlab4j-api/commit/97e31a7472238e7) Greg Messner *2017-06-14 14:55:21*


## gitlab4j-api-4.2.2
### No issue

**Updated for 4.2.2**


[6e8777e6d375031](https://github.com/gmessner/gitlab4j-api/commit/6e8777e6d375031) Greg Messner *2017-06-14 03:09:47*

**Fixed Javadoc issue on updateGroup().**


[e7a985c378e19e6](https://github.com/gmessner/gitlab4j-api/commit/e7a985c378e19e6) Greg Messner *2017-06-14 03:08:10*

**Cleaned up imports.**


[a2d2246b6be98c9](https://github.com/gmessner/gitlab4j-api/commit/a2d2246b6be98c9) Greg Messner *2017-06-14 02:56:43*

**Updated GroupApi to match latest spec.**


[c97793880d0959c](https://github.com/gmessner/gitlab4j-api/commit/c97793880d0959c) Greg Messner *2017-06-14 02:55:26*

**Cleaned up imports.**


[26061bf802b9f39](https://github.com/gmessner/gitlab4j-api/commit/26061bf802b9f39) Greg Messner *2017-06-14 02:54:14*


## gitlab4j-api-4.2.1
### No issue

**Mods related to GitLabApiConstants rename to Constants.**


[50199d8aa18b6a0](https://github.com/gmessner/gitlab4j-api/commit/50199d8aa18b6a0) Greg Messner *2017-06-11 05:27:23*

**Updated for release 4.2.1**


[9af60e3ea7ab89b](https://github.com/gmessner/gitlab4j-api/commit/9af60e3ea7ab89b) Greg Messner *2017-06-11 05:11:59*

**Now uses Constants enums instead of String literals.**


[3119c6fcace37c6](https://github.com/gmessner/gitlab4j-api/commit/3119c6fcace37c6) Greg Messner *2017-06-11 05:02:45*

**Initial check-in.**


[c9964e641e4455f](https://github.com/gmessner/gitlab4j-api/commit/c9964e641e4455f) Greg Messner *2017-06-11 05:02:01*

**Modified to use enums instead of String literals.**


[0038a55906b3312](https://github.com/gmessner/gitlab4j-api/commit/0038a55906b3312) Greg Messner *2017-06-10 21:38:44*

**Original check-in.**


[73d38d0092e2f99](https://github.com/gmessner/gitlab4j-api/commit/73d38d0092e2f99) Greg Messner *2017-06-10 21:38:16*

**Added info on PipelineApi.**


[515de8d19a2e235](https://github.com/gmessner/gitlab4j-api/commit/515de8d19a2e235) Greg Messner *2017-06-10 05:26:48*

**Changed how namespaceApi is set in the constructor.**


[a5350fb1c5bca38](https://github.com/gmessner/gitlab4j-api/commit/a5350fb1c5bca38) Greg Messner *2017-06-10 05:10:27*

**Added unit test for Pipeline.**


[e2b8eaed342caf9](https://github.com/gmessner/gitlab4j-api/commit/e2b8eaed342caf9) Greg Messner *2017-06-10 05:04:21*

**Added tag field.**


[4529032089e6352](https://github.com/gmessner/gitlab4j-api/commit/4529032089e6352) Greg Messner *2017-06-10 05:04:00*

**Cleaned up Javadocs.**


[484cf71e68f620c](https://github.com/gmessner/gitlab4j-api/commit/484cf71e68f620c) Greg Messner *2017-06-10 04:53:52*

**Code format cleanup**


[5efce766c6330a7](https://github.com/gmessner/gitlab4j-api/commit/5efce766c6330a7) Khosrow Moossavi *2017-06-10 04:39:07*

**Add Pipeline API**


[106003bf0a468d5](https://github.com/gmessner/gitlab4j-api/commit/106003bf0a468d5) Khosrow Moossavi *2017-06-10 04:39:07*

**Fixed grammer issue.**


[73420ee4716a844](https://github.com/gmessner/gitlab4j-api/commit/73420ee4716a844) Greg Messner *2017-05-27 21:34:58*

**ProjectApi enhancements.**


[a28210cc06ec645](https://github.com/gmessner/gitlab4j-api/commit/a28210cc06ec645) Greg Messner *2017-05-22 00:50:39*

**Added getIssues with page and per_page parameters**

 * getIssues() unless parameter returns only first page of issues.
 * Override this method with page and per_page parameters,
 * provides a way to read all issues page by page.
 * Signed-off-by: Matteo Facchinetti &lt;matteo.facchinetti@sirius-es.it&gt;

[65720abb1e78632](https://github.com/gmessner/gitlab4j-api/commit/65720abb1e78632) Matteo Facchinetti *2017-05-18 16:15:35*


## gitlab4j-api-4.2.0
### GitHub [#19](https://github.com/gmessner/gitlab4j-api/issues/19) Gitlab API v4  

**Added support for V4 of the GitLab API (#19).**


[958ef4c26b67766](https://github.com/gmessner/gitlab4j-api/commit/958ef4c26b67766) Greg Messner *2017-05-11 04:31:27*


### No issue

**Now tests GitLab API V4.**


[f4c2a4fe74c464b](https://github.com/gmessner/gitlab4j-api/commit/f4c2a4fe74c464b) Greg Messner *2017-05-11 04:57:34*

**Updated for release 4.2.0.**


[0c748730dda1a65](https://github.com/gmessner/gitlab4j-api/commit/0c748730dda1a65) Greg Messner *2017-05-11 04:46:51*


## gitlab4j-api-4.1.6
### GitHub [#20](https://github.com/gmessner/gitlab4j-api/issues/20) GroupApi.addGroup() returns Created   

**Fixed addGroup() to exepect CREATED (#20).**


[b1a89968ace9563](https://github.com/gmessner/gitlab4j-api/commit/b1a89968ace9563) Greg Messner *2017-05-05 20:11:56*


### No issue

**Cleaned up setIgnoreCerificateErrors().**


[8336695f81ff883](https://github.com/gmessner/gitlab4j-api/commit/8336695f81ff883) Greg Messner *2017-05-05 20:13:31*

**GitLabApi class now tracks Session if session login was used.**


[fc7515b2beefdb8](https://github.com/gmessner/gitlab4j-api/commit/fc7515b2beefdb8) Greg Messner *2017-04-27 19:02:34*

**Initial check-in.**


[70d987fb63e4cbf](https://github.com/gmessner/gitlab4j-api/commit/70d987fb63e4cbf) Greg Messner *2017-04-26 20:41:09*

**Fixed typo in CommitsApi sample.**


[29375a268184668](https://github.com/gmessner/gitlab4j-api/commit/29375a268184668) Greg Messner *2017-04-15 17:11:44*

**Fixed typo.**


[81c8d18bd055483](https://github.com/gmessner/gitlab4j-api/commit/81c8d18bd055483) Greg Messner *2017-04-15 16:57:02*

**Added more code samples.**


[5e8ee196d8c46df](https://github.com/gmessner/gitlab4j-api/commit/5e8ee196d8c46df) Greg Messner *2017-04-15 15:59:01*


## gitlab4j-api-4.1.5
### No issue

**Updated for 4.1.5.**


[9c0e181ecdf64ee](https://github.com/gmessner/gitlab4j-api/commit/9c0e181ecdf64ee) Greg Messner *2017-04-15 03:19:15*

**Fixed parameter based createProject().**


[47e56e5e8c7e539](https://github.com/gmessner/gitlab4j-api/commit/47e56e5e8c7e539) Greg Messner *2017-04-15 03:18:10*

**Updated for release 4.1.4.**


[4d36f7087ee01d5](https://github.com/gmessner/gitlab4j-api/commit/4d36f7087ee01d5) Greg Messner *2017-04-15 02:29:48*


## gitlab4j-api-4.1.4
### No issue

**Added support for /namespaces calls.**


[a42bc84e33cc526](https://github.com/gmessner/gitlab4j-api/commit/a42bc84e33cc526) Greg Messner *2017-04-15 02:17:59*

**Initial check-in.**


[ec7f44a22b87e42](https://github.com/gmessner/gitlab4j-api/commit/ec7f44a22b87e42) Greg Messner *2017-04-15 02:17:31*


## gitlab4j-api-4.1.3
### No issue

**Mods related to create project fixes.**


[054754d5b21bf4e](https://github.com/gmessner/gitlab4j-api/commit/054754d5b21bf4e) Greg Messner *2017-04-15 01:47:17*

**Initial check-in.**


[0c6e6baa2b848a7](https://github.com/gmessner/gitlab4j-api/commit/0c6e6baa2b848a7) Greg Messner *2017-04-15 01:46:37*

**Updated for release 4.1.2.**


[451a8d3042bdae8](https://github.com/gmessner/gitlab4j-api/commit/451a8d3042bdae8) Greg Messner *2017-04-11 18:06:38*


## gitlab4j-api-4.1.2
### No issue

**Fixed Javadoc error.**


[c92435ecc1f0dd8](https://github.com/gmessner/gitlab4j-api/commit/c92435ecc1f0dd8) Greg Messner *2017-04-10 05:33:05*

**Completed webhooks support.**


[6404e89ba6e7625](https://github.com/gmessner/gitlab4j-api/commit/6404e89ba6e7625) Greg Messner *2017-04-10 05:26:56*

**Initial check-in.**


[beeea2e9d53438f](https://github.com/gmessner/gitlab4j-api/commit/beeea2e9d53438f) Greg Messner *2017-04-10 05:26:31*


## gitlab4j-api-4.1.1
### No issue

**Corrected refactoring issue.**


[6d10a456c36b8fa](https://github.com/gmessner/gitlab4j-api/commit/6d10a456c36b8fa) Greg Messner *2017-04-10 03:42:28*

**Mods for webhook support.**


[80d32ac66771304](https://github.com/gmessner/gitlab4j-api/commit/80d32ac66771304) Greg Messner *2017-04-10 03:30:55*

**Initial check-in to support webhook handling.**


[e9f806a4b1584e8](https://github.com/gmessner/gitlab4j-api/commit/e9f806a4b1584e8) Greg Messner *2017-04-10 03:30:20*

**Moved to utils for better API structure.**


[ae103920614d339](https://github.com/gmessner/gitlab4j-api/commit/ae103920614d339) Greg Messner *2017-04-10 03:28:52*

**Refactored as an abstract class to support TagPushEvent.**


[27ddced2cf705d6](https://github.com/gmessner/gitlab4j-api/commit/27ddced2cf705d6) Greg Messner *2017-04-10 03:28:12*

**Refactored as an abstract class to support TagPushEvent.**


[ee722f1d7a696b5](https://github.com/gmessner/gitlab4j-api/commit/ee722f1d7a696b5) Greg Messner *2017-04-10 03:27:47*


## gitlab4j-api-4.1.0
### No issue

**Final Javadoc clean-up.**


[315693f282d55bd](https://github.com/gmessner/gitlab4j-api/commit/315693f282d55bd) Greg Messner *2017-04-09 03:51:27*

**Javadoc clean-up.**


[3fc7ad599503755](https://github.com/gmessner/gitlab4j-api/commit/3fc7ad599503755) Greg Messner *2017-04-09 02:58:51*

**Re-worked to be consistient with rest of API.**


[91fbe829bb012e9](https://github.com/gmessner/gitlab4j-api/commit/91fbe829bb012e9) Greg Messner *2017-04-09 02:58:10*

**Re-worked hook methods to be consistient with rest of API.**


[f95da6d082fce0a](https://github.com/gmessner/gitlab4j-api/commit/f95da6d082fce0a) Greg Messner *2017-04-09 02:57:14*

**Added job_events.**


[2fd7ff41a9b2261](https://github.com/gmessner/gitlab4j-api/commit/2fd7ff41a9b2261) Greg Messner *2017-04-09 02:55:42*

**Switched version to 4.1.0-SNAPSHOT.**


[e689753cfa775b6](https://github.com/gmessner/gitlab4j-api/commit/e689753cfa775b6) Greg Messner *2017-04-09 02:55:13*

**General code cleanup.**


[ae292e7b08481ec](https://github.com/gmessner/gitlab4j-api/commit/ae292e7b08481ec) Greg Messner *2017-04-04 21:37:41*

**Updated for release 4.0.9**


[882e016e1cf7dd4](https://github.com/gmessner/gitlab4j-api/commit/882e016e1cf7dd4) Greg Messner *2017-04-01 17:43:53*


## gitlab4j-api-4.0.9
### No issue

**Added support for creating a tag with the release notes read from a file.**


[c060e34d64eabea](https://github.com/gmessner/gitlab4j-api/commit/c060e34d64eabea) Greg Messner *2017-03-29 03:49:57*

**Cleaned up Javadocs.**


[6e759a556820eec](https://github.com/gmessner/gitlab4j-api/commit/6e759a556820eec) Greg Messner *2017-03-29 03:49:23*

**Updated for release 4.0.8.**


[d52af9687067ed0](https://github.com/gmessner/gitlab4j-api/commit/d52af9687067ed0) Greg Messner *2017-03-26 17:32:06*


## gitlab4j-api-4.0.8
### No issue

**Added support for providing Jersey client properties.**


[a9be1289084b9da](https://github.com/gmessner/gitlab4j-api/commit/a9be1289084b9da) Greg Messner *2017-03-26 01:18:20*

**Cleaned up formatting.**


[2b9a7b970b1da84](https://github.com/gmessner/gitlab4j-api/commit/2b9a7b970b1da84) Greg Messner *2017-03-26 01:17:47*

**Fixed getTags().**


[ae761fd854da02f](https://github.com/gmessner/gitlab4j-api/commit/ae761fd854da02f) Greg Messner *2017-03-26 01:15:19*

**Added support for JaCoCo code coverage reports.**


[f70ac708a134bf2](https://github.com/gmessner/gitlab4j-api/commit/f70ac708a134bf2) Greg Messner *2017-03-26 00:34:10*


## gitlab4j-api-4.0.7
### No issue

**Modified handle() to only return the exception not throw it.**


[4aede591f840754](https://github.com/gmessner/gitlab4j-api/commit/4aede591f840754) Greg Messner *2017-03-26 00:00:31*

**Updated for release 4.0.6**


[577088d30d715e1](https://github.com/gmessner/gitlab4j-api/commit/577088d30d715e1) Greg Messner *2017-03-23 18:17:31*


## gitlab4j-api-4.0.6
### No issue

**add an API to get a list of project's issues**

 * Signed-off-by: Matteo Facchinetti &lt;matteo.facchinetti@sirius-es.it&gt;

[07c3aab03fd2661](https://github.com/gmessner/gitlab4j-api/commit/07c3aab03fd2661) Matteo Facchinetti *2017-03-17 13:23:10*

**Add an API to create tags in a repository**


[2dca5b038e487ff](https://github.com/gmessner/gitlab4j-api/commit/2dca5b038e487ff) Julien Lafourcade *2017-03-10 15:32:39*

**Added support for processing the secret token in X-GitLab-Token.**


[2ffa20d1fb59ac4](https://github.com/gmessner/gitlab4j-api/commit/2ffa20d1fb59ac4) Greg Messner *2017-02-21 03:19:40*

**Javadocs now opened in Frames type.**


[4e42d84eb94088d](https://github.com/gmessner/gitlab4j-api/commit/4e42d84eb94088d) Greg Messner *2017-02-20 17:46:30*

**Changed title.**


[8dd343719490678](https://github.com/gmessner/gitlab4j-api/commit/8dd343719490678) Greg Messner *2017-02-20 17:39:41*

**Fixed Javadocs link.**


[f6f58b5c7ef4861](https://github.com/gmessner/gitlab4j-api/commit/f6f58b5c7ef4861) Greg Messner *2017-02-20 17:37:39*

**Added info for using with Gradle and Maven.**


[fd7a005028f1d78](https://github.com/gmessner/gitlab4j-api/commit/fd7a005028f1d78) Greg Messner *2017-02-20 17:30:25*

**Updated description.**


[3ead5515663b072](https://github.com/gmessner/gitlab4j-api/commit/3ead5515663b072) Greg Messner *2017-02-19 01:04:57*


## gitlab4j-api-4.0.5
### No issue

**Changed description.**


[2d585846faa5f8a](https://github.com/gmessner/gitlab4j-api/commit/2d585846faa5f8a) Greg Messner *2017-02-19 00:51:57*

**Modified for group and artifact name changes.**


[56315f865f8b4a4](https://github.com/gmessner/gitlab4j-api/commit/56315f865f8b4a4) Greg Messner *2017-02-19 00:50:54*

**Updated title.**


[25fbdde7bbcbdae](https://github.com/gmessner/gitlab4j-api/commit/25fbdde7bbcbdae) Greg Messner *2017-02-19 00:15:25*

**Updated for GitLab4J rename.**


[a0a7248965bdfe1](https://github.com/gmessner/gitlab4j-api/commit/a0a7248965bdfe1) Greg Messner *2017-02-19 00:04:56*

**Mods for renaming com.messners.gitlab.api to org.gitlab4j.api**


[3103fd6c3af7957](https://github.com/gmessner/gitlab4j-api/commit/3103fd6c3af7957) Greg Messner *2017-02-19 00:04:07*

**Renamed root package from com.messners.gitlab.api to org.gitlab4j.api**


[266afaff03a0ae1](https://github.com/gmessner/gitlab4j-api/commit/266afaff03a0ae1) Greg Messner *2017-02-19 00:00:23*


## gitlab-api-4.0.4
### No issue

**Fixed Javadocs.**


[20a50d2574ee2ff](https://github.com/gmessner/gitlab4j-api/commit/20a50d2574ee2ff) Greg Messner *2017-02-18 21:46:24*

**Renamed due to collision with non-test class.**


[95b76278ea35027](https://github.com/gmessner/gitlab4j-api/commit/95b76278ea35027) Greg Messner *2017-02-18 21:11:34*

**Added tests for getRepositoryArchive().**


[3c710091cbf7078](https://github.com/gmessner/gitlab4j-api/commit/3c710091cbf7078) Greg Messner *2017-02-18 21:10:41*

**Added getRepositoryArchive() method that downloads the archive to a file.**


[194821f1c737c8d](https://github.com/gmessner/gitlab4j-api/commit/194821f1c737c8d) Greg Messner *2017-02-18 21:00:20*

**Initial check-in.**


[3e3415287a5ce10](https://github.com/gmessner/gitlab4j-api/commit/3e3415287a5ce10) Greg Messner *2017-02-18 20:59:54*

**Added new method to repository API to get the archive of the repository**


[9c92ffab2f8ff5a](https://github.com/gmessner/gitlab4j-api/commit/9c92ffab2f8ff5a) Julien Lafourcade *2017-02-16 09:49:34*

**Add new method on RepositoryApi to get tree recursively**


[07407a5776d0a71](https://github.com/gmessner/gitlab4j-api/commit/07407a5776d0a71) Julien Lafourcade *2017-02-10 16:04:37*


## gitlab-api-4.0.3
### No issue

**Misc. webhook related fixes.**


[a2f83183f0d1a67](https://github.com/gmessner/gitlab4j-api/commit/a2f83183f0d1a67) Greg Messner *2017-02-08 00:39:43*

**Initial check-in.**


[3b03f9710aefd23](https://github.com/gmessner/gitlab4j-api/commit/3b03f9710aefd23) Greg Messner *2017-02-08 00:39:13*


## gitlab-api-4.0.2
### No issue

**Mods to support GitLab 8.**


[b0d8f947ae603f5](https://github.com/gmessner/gitlab4j-api/commit/b0d8f947ae603f5) Greg Messner *2017-02-07 08:17:43*

**Now handles all date strings received from GitLab.**


[f05942ff93f6c81](https://github.com/gmessner/gitlab4j-api/commit/f05942ff93f6c81) Greg Messner *2017-02-07 08:17:06*


## gitlab-api-4.0.1
### No issue

**Reverted to no longer use OSS parent pom.**


[abe7c486958b97e](https://github.com/gmessner/gitlab4j-api/commit/abe7c486958b97e) Greg Messner *2017-02-06 20:27:13*


## gitlab-api-4.0.0
### No issue

**More mods to support GitLab 8**


[e602c3430e67606](https://github.com/gmessner/gitlab4j-api/commit/e602c3430e67606) Greg Messner *2017-02-06 08:23:00*

**More mods to support GitLab 8**


[c2af9a23627fee7](https://github.com/gmessner/gitlab4j-api/commit/c2af9a23627fee7) Greg Messner *2017-02-06 08:22:47*

**Now supports GitLab 8, bumped version to 4.0.0**


[382ba2cc9601679](https://github.com/gmessner/gitlab4j-api/commit/382ba2cc9601679) Greg Messner *2017-02-06 04:26:55*

**Mods for GitLab 8 support.**


[a28fc55f3132720](https://github.com/gmessner/gitlab4j-api/commit/a28fc55f3132720) Greg Messner *2017-02-06 04:21:49*

**Initial check-in.**


[1d5d936c7367847](https://github.com/gmessner/gitlab4j-api/commit/1d5d936c7367847) Greg Messner *2017-02-06 04:21:01*


## gitlab-api-3.0.1
### No issue

**Now uses OSS Parent pom.**


[f0e5a51db54cd30](https://github.com/gmessner/gitlab4j-api/commit/f0e5a51db54cd30) Greg Messner *2017-02-02 05:25:43*

**Cleaned up imports.**


[c270e98574dbdb0](https://github.com/gmessner/gitlab4j-api/commit/c270e98574dbdb0) Greg Messner *2017-01-31 20:48:40*


## gitlab-api-3.0.0
### No issue

**More Javadoc fixes.**


[32cf4cad6b10000](https://github.com/gmessner/gitlab4j-api/commit/32cf4cad6b10000) Greg Messner *2017-01-31 04:13:58*

**Mods for new Jackson version.**


[1fa8625200ef4eb](https://github.com/gmessner/gitlab4j-api/commit/1fa8625200ef4eb) Greg Messner *2017-01-31 04:13:32*

**Updated versions for JDK, Jersey, JUnit, and Jackson.**


[7ec6815c1424920](https://github.com/gmessner/gitlab4j-api/commit/7ec6815c1424920) Greg Messner *2017-01-31 04:12:12*

**Fixed javadoc issues.**


[c526865f398d7fc](https://github.com/gmessner/gitlab4j-api/commit/c526865f398d7fc) Greg Messner *2017-01-31 01:40:23*

**Preparing for 3.0.0 release.**


[8ba7654966504d6](https://github.com/gmessner/gitlab4j-api/commit/8ba7654966504d6) Greg Messner *2017-01-31 01:11:51*

**Standardized code formatting.**


[9226d4807ded4a1](https://github.com/gmessner/gitlab4j-api/commit/9226d4807ded4a1) Greg Messner *2017-01-31 01:04:52*

**Will now automatically skip tests if not configured correctly.**


[58daa255a0aefb7](https://github.com/gmessner/gitlab4j-api/commit/58daa255a0aefb7) Greg Messner *2017-01-31 01:04:16*

**Fixed createBranch() and added deleteBranch().**


[8ebcd09a7f69718](https://github.com/gmessner/gitlab4j-api/commit/8ebcd09a7f69718) Greg Messner *2017-01-31 01:03:20*

**addMember should return 201 CREATED rather than 200 OK**


[302c26f719f33c8](https://github.com/gmessner/gitlab4j-api/commit/302c26f719f33c8) 六幻 *2015-07-16 07:53:24*

**add a getTree method with 2 more additional parameters**


[62309b4a13ca954](https://github.com/gmessner/gitlab4j-api/commit/62309b4a13ca954) 六幻 *2015-05-20 07:10:31*

**recover groupId**


[f3593803a9ad66e](https://github.com/gmessner/gitlab4j-api/commit/f3593803a9ad66e) 六幻 *2015-05-20 07:08:15*

**should use GET rather than PUT when get a tree of a projecct**


[8f59e88fd6c3b52](https://github.com/gmessner/gitlab4j-api/commit/8f59e88fd6c3b52) 六幻 *2015-05-20 06:55:28*

**spelling err:ifrom -> from.**


[2473bc22f7a3a12](https://github.com/gmessner/gitlab4j-api/commit/2473bc22f7a3a12) 六幻 *2015-05-18 06:15:16*

**Fixed formatting issue.**


[5becb8b1746f784](https://github.com/gmessner/gitlab4j-api/commit/5becb8b1746f784) Greg Messner *2015-05-16 23:49:49*

**Added RepositoryFileApi example.**


[e06ef8e6650bfa3](https://github.com/gmessner/gitlab4j-api/commit/e06ef8e6650bfa3) Greg Messner *2015-05-16 23:49:04*


## gitlab-api-2.0.4
### No issue

**Updates for the new ServicesApi.**


[e0cff1d0adffdf0](https://github.com/gmessner/gitlab4j-api/commit/e0cff1d0adffdf0) Greg Messner *2015-05-25 04:31:48*

**Implementation of the documented Services API.**

 * Currently, only a subset of the service API is documented.
 * https://github.com/gitlabhq/gitlabhq/blob/master/doc/api/services.md

[18e25fb719980b3](https://github.com/gmessner/gitlab4j-api/commit/18e25fb719980b3) Mirko Friedenhagen *2015-05-22 20:58:25*


## gitlab-api-2.0.3
### No issue

**Fixed javadoc warnings.**


[ae564a49eefdf65](https://github.com/gmessner/gitlab4j-api/commit/ae564a49eefdf65) Greg Messner *2015-05-21 01:27:15*


## gitlab-api-2.0.2
### No issue

**Cleaned up formatting.**


[d668912d86c5120](https://github.com/gmessner/gitlab4j-api/commit/d668912d86c5120) Greg Messner *2015-05-21 01:12:28*

**Fixed spelling in comment for RepositoryFileApi.**


[21cfc1c547ddd01](https://github.com/gmessner/gitlab4j-api/commit/21cfc1c547ddd01) Greg Messner *2015-05-18 08:08:12*


## gitlab-api-2.0.1
### No issue

**Minor format changes from previous merge.**


[67b1e3b01ce8ba9](https://github.com/gmessner/gitlab4j-api/commit/67b1e3b01ce8ba9) Greg Messner *2015-05-15 18:15:49*

**fix:PUT mehtod is not workable at all.**


[cb0fee18fbfaf95](https://github.com/gmessner/gitlab4j-api/commit/cb0fee18fbfaf95) 六幻 *2015-05-14 08:33:53*

**add a RepositoryFileApi instance to GilLabApi.**


[db48ec9055a77c0](https://github.com/gmessner/gitlab4j-api/commit/db48ec9055a77c0) 六幻 *2015-05-14 03:07:14*

**add more comments.**


[3e42bef26f46633](https://github.com/gmessner/gitlab4j-api/commit/3e42bef26f46633) 六幻 *2015-05-14 02:42:43*

**Add api: Create, read, update and delete repository files.**


[1d8b0a75eb26069](https://github.com/gmessner/gitlab4j-api/commit/1d8b0a75eb26069) 六幻 *2015-05-14 02:36:58*

**add getRawBlobCotent method**


[48c8adfaa60d4b4](https://github.com/gmessner/gitlab4j-api/commit/48c8adfaa60d4b4) 六幻 *2015-05-13 10:48:44*

**rewrite findUsers method.**


[409e5ea820b64f5](https://github.com/gmessner/gitlab4j-api/commit/409e5ea820b64f5) 六幻 *2015-05-13 10:42:32*

**delete unused import**


[604c8388a6f46f8](https://github.com/gmessner/gitlab4j-api/commit/604c8388a6f46f8) 六幻 *2015-05-13 09:05:22*

**fix conflicts**


[78af4e54f2c7000](https://github.com/gmessner/gitlab4j-api/commit/78af4e54f2c7000) 六幻 *2015-05-13 09:02:52*

**should use GET rather than PUT when get a tree of a projecct**


[c4e0b4edb4b7326](https://github.com/gmessner/gitlab4j-api/commit/c4e0b4edb4b7326) 六幻 *2015-05-13 03:38:29*

**fix spelling mistake**


[c5be44ba1bd35e3](https://github.com/gmessner/gitlab4j-api/commit/c5be44ba1bd35e3) 六幻 *2015-05-13 03:29:07*

**add findUsers method.**


[0e3aaaa8d2e123b](https://github.com/gmessner/gitlab4j-api/commit/0e3aaaa8d2e123b) 六幻 *2015-05-12 12:32:19*

**add findUsers method.**


[3d855d1f91be4a2](https://github.com/gmessner/gitlab4j-api/commit/3d855d1f91be4a2) 六幻 *2015-05-12 12:17:22*

**add createProject method with full params.**


[04a31e4b814ffb2](https://github.com/gmessner/gitlab4j-api/commit/04a31e4b814ffb2) 六幻 *2015-05-12 09:23:32*


## gitlab-api-2.0.0
### No issue

**Updated version.**


[4b372165259bf68](https://github.com/gmessner/gitlab4j-api/commit/4b372165259bf68) Greg Messner *2015-05-13 07:50:41*

**Remeved exec permission.**


[99d1251b154c07d](https://github.com/gmessner/gitlab4j-api/commit/99d1251b154c07d) Greg Messner *2015-05-13 07:48:29*

**refactoring: simplify / optimize / eliminate duplicates**


[1fdccb141e3e5b1](https://github.com/gmessner/gitlab4j-api/commit/1fdccb141e3e5b1) Patrik Beno *2015-05-06 20:10:20*

**upgrade to JAX-RS/Jersey 2.x API**


[ebe5d24b22998ab](https://github.com/gmessner/gitlab4j-api/commit/ebe5d24b22998ab) Patrik Beno *2015-05-06 19:31:01*

**new: GitLabApi.create(url, user, pass)**


[073f1918cafeae2](https://github.com/gmessner/gitlab4j-api/commit/073f1918cafeae2) Patrik Beno *2015-04-08 21:06:35*

**fix: successful session login returns HTTP 201 Created (not 200 OK)**


[979c0d965ae798b](https://github.com/gmessner/gitlab4j-api/commit/979c0d965ae798b) Patrik Beno *2015-04-08 10:28:49*


## gitlab-api-1.0.15
### No issue

**Made getIgnoreCertificateErrors() public.**


[b89d79bbd2656aa](https://github.com/gmessner/gitlab4j-api/commit/b89d79bbd2656aa) Greg Messner *2014-12-27 05:46:56*


## gitlab-api-1.0.13
### No issue

**Changed setIgnoreCerificateErrors() to public.**


[10c59ef9492a9c5](https://github.com/gmessner/gitlab4j-api/commit/10c59ef9492a9c5) Greg Messner *2014-12-25 15:41:47*


## gitlab-api-1.0.12
### No issue

**Cleaned up Javadocs.**


[a5ab678dd86be20](https://github.com/gmessner/gitlab4j-api/commit/a5ab678dd86be20) Greg Messner *2014-12-04 06:34:58*


## gitlab-api-1.0.11
### No issue

**Added createBranch support for 6.8.x**


[5a4138da0f36624](https://github.com/gmessner/gitlab4j-api/commit/5a4138da0f36624) Greg Messner *2014-12-04 06:27:03*


## gitlab-api-1.0.10
### No issue

**Removed parent element and inlined all the plugins.**


[567582f783c093b](https://github.com/gmessner/gitlab4j-api/commit/567582f783c093b) Greg Messner *2014-12-02 00:00:38*


## gitlab-api-1.0.9
### No issue

**Completed Javadocs.**


[2c58b56fa825733](https://github.com/gmessner/gitlab4j-api/commit/2c58b56fa825733) Greg Messner *2014-03-29 17:11:54*

**No longer used.**


[d099773e95c3c66](https://github.com/gmessner/gitlab4j-api/commit/d099773e95c3c66) Greg Messner *2014-03-29 17:11:14*

**Added SessionApi info.**


[40ba792e90709b6](https://github.com/gmessner/gitlab4j-api/commit/40ba792e90709b6) Greg Messner *2014-03-28 05:25:54*

**Cleaned up Javadocs.**


[413f1c2cae87c52](https://github.com/gmessner/gitlab4j-api/commit/413f1c2cae87c52) Greg Messner *2014-03-28 05:16:22*

**Added SessionApi.**


[d11dff6708be1fc](https://github.com/gmessner/gitlab4j-api/commit/d11dff6708be1fc) Greg Messner *2014-03-28 05:15:03*

**Fixed mis-spell in sample code.**


[e8c27ffa31a58ab](https://github.com/gmessner/gitlab4j-api/commit/e8c27ffa31a58ab) Greg Messner *2014-03-24 00:09:45*


## gitlab-api-1.0.8
### No issue

**Cleaned up markdown.**


[46756155182b6f9](https://github.com/gmessner/gitlab4j-api/commit/46756155182b6f9) Greg Messner *2014-03-24 00:04:53*

**Added markdown.**


[c7dc12e36fc9306](https://github.com/gmessner/gitlab4j-api/commit/c7dc12e36fc9306) Greg Messner *2014-03-24 00:01:20*

**Added initial real content.**


[4e242152d313e0a](https://github.com/gmessner/gitlab4j-api/commit/4e242152d313e0a) Greg Messner *2014-03-23 23:53:46*

**Added getUsers(int page, int perPage).**


[fe5a7913c1fc111](https://github.com/gmessner/gitlab4j-api/commit/fe5a7913c1fc111) Greg Messner *2014-03-23 23:52:52*


## gitlab-api-1.0.7
### No issue

**Changed handling of unexpected HTTP status.**


[a6cb160c34eda02](https://github.com/gmessner/gitlab4j-api/commit/a6cb160c34eda02) Greg Messner *2014-03-21 07:38:47*

**Changed how the HTTP status is determined.**


[206dba0b4c90081](https://github.com/gmessner/gitlab4j-api/commit/206dba0b4c90081) Greg Messner *2014-03-21 07:17:42*

**Fixed NPE.**


[0c2c14e9a005941](https://github.com/gmessner/gitlab4j-api/commit/0c2c14e9a005941) Greg Messner *2014-03-21 05:02:33*


## gitlab-api-1.0.6
### No issue

**Added description property.**


[cf5b8e7d67f857d](https://github.com/gmessner/gitlab4j-api/commit/cf5b8e7d67f857d) Greg Messner *2014-03-18 13:50:03*

**Added getProjetc(STring group, String project).**


[41182514f2ea171](https://github.com/gmessner/gitlab4j-api/commit/41182514f2ea171) Greg Messner *2014-03-18 13:49:10*

**Added getGroupApi().**


[01303067001e3b5](https://github.com/gmessner/gitlab4j-api/commit/01303067001e3b5) Greg Messner *2014-03-18 13:46:04*


## gitlab-api-1.0.5
### No issue

**Refactored HTTP method implementations.**


[aecdac9056588e2](https://github.com/gmessner/gitlab4j-api/commit/aecdac9056588e2) Greg Messner *2014-03-16 07:15:42*

**Initial check-in.**


[2b2aaf0e9977235](https://github.com/gmessner/gitlab4j-api/commit/2b2aaf0e9977235) Greg Messner *2014-03-16 07:15:11*

**Added isValid() method.**


[1d68180adeead40](https://github.com/gmessner/gitlab4j-api/commit/1d68180adeead40) Greg Messner *2014-03-16 07:14:40*

**Now tests for bad JSON.**


[4b96587f939e688](https://github.com/gmessner/gitlab4j-api/commit/4b96587f939e688) Greg Messner *2014-03-16 07:13:29*

**Initial check-in.**


[37afe15d2b51c1f](https://github.com/gmessner/gitlab4j-api/commit/37afe15d2b51c1f) Greg Messner *2014-03-16 07:12:57*

**Completed implementation.**


[5f40b8dd687f6f4](https://github.com/gmessner/gitlab4j-api/commit/5f40b8dd687f6f4) Greg Messner *2014-03-13 00:23:00*

**Completed implementation of branch calls.**


[67ea01027ecfc44](https://github.com/gmessner/gitlab4j-api/commit/67ea01027ecfc44) Greg Messner *2014-03-13 00:22:26*

**Added modifyUser().**


[5840f3332a7571e](https://github.com/gmessner/gitlab4j-api/commit/5840f3332a7571e) Greg Messner *2014-03-13 00:21:28*

**Added put() methods.**


[d80e4b15ebc9953](https://github.com/gmessner/gitlab4j-api/commit/d80e4b15ebc9953) Greg Messner *2014-03-13 00:02:03*

**Completed ProjectHook implementation.**


[b38431015c0016f](https://github.com/gmessner/gitlab4j-api/commit/b38431015c0016f) Greg Messner *2014-03-13 00:01:18*

**Added testProjectHook().**


[5f399bf0a2a30b4](https://github.com/gmessner/gitlab4j-api/commit/5f399bf0a2a30b4) Greg Messner *2014-03-13 00:00:27*

**Initial check-in.**


[14babb0863d4da8](https://github.com/gmessner/gitlab4j-api/commit/14babb0863d4da8) Greg Messner *2014-03-12 22:47:10*


## gitlab-api-1.0.4
### No issue

**Changed boolean to Boolean.**


[764787fae029493](https://github.com/gmessner/gitlab4j-api/commit/764787fae029493) Greg Messner *2014-03-05 06:39:26*

**Added support for create user API call.**


[41f6f41f2f1cc7d](https://github.com/gmessner/gitlab4j-api/commit/41f6f41f2f1cc7d) Greg Messner *2014-03-05 06:33:21*

**Cleaned up formatting.**


[c008705bd631683](https://github.com/gmessner/gitlab4j-api/commit/c008705bd631683) Greg Messner *2014-03-04 05:41:47*

**Added additional Group API calls.**


[514e90464ed631d](https://github.com/gmessner/gitlab4j-api/commit/514e90464ed631d) Greg Messner *2014-03-04 05:39:52*


## gitlab-api-1.0.3
### No issue

**Removed dependency on jsonassert.**


[50fdfe7bda59d5f](https://github.com/gmessner/gitlab4j-api/commit/50fdfe7bda59d5f) Greg Messner *2014-03-03 05:58:18*

**Fixed issues exposed by unit tests.**


[5ffec8437251140](https://github.com/gmessner/gitlab4j-api/commit/5ffec8437251140) Greg Messner *2014-03-03 05:53:34*

**Changes due to refactored GitLabApi.**


[3489473ff73bd89](https://github.com/gmessner/gitlab4j-api/commit/3489473ff73bd89) Greg Messner *2014-03-03 05:48:33*

**Completed unit tests.**


[dae00ce19e82c0f](https://github.com/gmessner/gitlab4j-api/commit/dae00ce19e82c0f) Greg Messner *2014-03-03 05:47:45*

**Initial check-in.**


[f71e01fce9a4231](https://github.com/gmessner/gitlab4j-api/commit/f71e01fce9a4231) Greg Messner *2014-03-03 05:47:05*

**Renamed SystemHook.json to system-hook.json.**


[d7af9c37241a205](https://github.com/gmessner/gitlab4j-api/commit/d7af9c37241a205) Greg Messner *2014-03-03 05:46:42*

**Fixed issues exposed by unit tests.**


[e6dc786bb975ab2](https://github.com/gmessner/gitlab4j-api/commit/e6dc786bb975ab2) Greg Messner *2014-03-03 05:46:09*

**Initial check-in.**


[da9b94cbc86a2a0](https://github.com/gmessner/gitlab4j-api/commit/da9b94cbc86a2a0) Greg Messner *2014-03-03 05:41:45*

**Initial check-in.**


[f2d7d11ab24955f](https://github.com/gmessner/gitlab4j-api/commit/f2d7d11ab24955f) Greg Messner *2014-03-03 05:40:52*

**Added jsonassert dependency, is needed to unit test.**


[bfa718288825f7c](https://github.com/gmessner/gitlab4j-api/commit/bfa718288825f7c) Greg Messner *2014-03-02 22:12:49*

**Refactored to group like API calls into separate classes.**


[e131114e042b784](https://github.com/gmessner/gitlab4j-api/commit/e131114e042b784) Greg Messner *2014-03-02 22:09:19*

**Initial check-in.**


[9925b2b2a91ea3d](https://github.com/gmessner/gitlab4j-api/commit/9925b2b2a91ea3d) Greg Messner *2014-03-02 22:08:49*

**Refactored GitLabApi to group like API calls into a separate class, this**

 * is the initial check-in of that class.

[0a3997537fbfa24](https://github.com/gmessner/gitlab4j-api/commit/0a3997537fbfa24) Greg Messner *2014-03-02 22:08:25*

**Refactored to move all message model classes to models package.**


[fbbe0dbfb93e1e4](https://github.com/gmessner/gitlab4j-api/commit/fbbe0dbfb93e1e4) Greg Messner *2014-03-02 22:06:11*

**Consolidated tests.**


[56afbdf14936843](https://github.com/gmessner/gitlab4j-api/commit/56afbdf14936843) Greg Messner *2014-03-01 04:03:45*

**Added getBranch() to simplify pulling the branch out of the refs.**


[dc9821278f3baf6](https://github.com/gmessner/gitlab4j-api/commit/dc9821278f3baf6) Greg Messner *2014-03-01 03:55:16*

**Added a few merge request related calls.**


[a913a19bb571d7c](https://github.com/gmessner/gitlab4j-api/commit/a913a19bb571d7c) Greg Messner *2014-03-01 03:54:36*

**Mods to make the Jersey client work in a server environment.**


[ba09289cb11427e](https://github.com/gmessner/gitlab4j-api/commit/ba09289cb11427e) Greg Messner *2014-02-28 06:42:59*

**Changed Number to Integer.**


[92e14f812d2f7a4](https://github.com/gmessner/gitlab4j-api/commit/92e14f812d2f7a4) Greg Messner *2014-02-26 05:09:40*

**Added marshal() and renamed unmarshall() to unmarshal().**


[c88bd0ad5d504c8](https://github.com/gmessner/gitlab4j-api/commit/c88bd0ad5d504c8) Greg Messner *2014-02-26 02:15:04*

**Renamed JacksonJsonConfig to JacksonJson.**


[066705e929741c3](https://github.com/gmessner/gitlab4j-api/commit/066705e929741c3) Greg Messner *2014-02-26 01:35:26*

**Added getObjectMapper().**


[e3d662450fafa38](https://github.com/gmessner/gitlab4j-api/commit/e3d662450fafa38) Greg Messner *2014-02-25 04:17:10*

**Changed junit version to 4.11.**


[6c63caa348bb651](https://github.com/gmessner/gitlab4j-api/commit/6c63caa348bb651) Greg Messner *2014-02-24 01:39:06*

**No longer used.**


[849689831029ed3](https://github.com/gmessner/gitlab4j-api/commit/849689831029ed3) Greg Messner *2014-02-24 01:31:29*

**Aded comments about use.**


[a5cb28739abd5f9](https://github.com/gmessner/gitlab4j-api/commit/a5cb28739abd5f9) Greg Messner *2014-02-23 19:08:29*

**Properly specified bin.**


[615ad1a7929d344](https://github.com/gmessner/gitlab4j-api/commit/615ad1a7929d344) Greg Messner *2014-02-23 19:03:22*

**Initial check-in.**


[04af0046254fb0c](https://github.com/gmessner/gitlab4j-api/commit/04af0046254fb0c) Greg Messner *2014-02-23 18:59:25*


## gitlab-api-1.0.2
### No issue

**Changed version to 1.0.2-SNAPSHOT.**


[867604f05824421](https://github.com/gmessner/gitlab4j-api/commit/867604f05824421) Greg Messner *2014-02-21 04:09:51*

**Added jdk.version property.**


[466dd985181115c](https://github.com/gmessner/gitlab4j-api/commit/466dd985181115c) gmessner *2014-02-20 08:14:19*

**Moved the maven-pgp-plugin into a profile.**


[2c6b8878c291466](https://github.com/gmessner/gitlab4j-api/commit/2c6b8878c291466) gmessner *2014-02-20 08:06:38*

**Reset version to 1.0.0-SNAPSHOT and added version to maven-pgp-plugin.**


[d494e7f1f414aa2](https://github.com/gmessner/gitlab4j-api/commit/d494e7f1f414aa2) gmessner *2014-02-20 07:51:45*


## gitlab-api-1.0.0
### No issue

**Various tweaks for distribution.**


[9a20b8de5f18e20](https://github.com/gmessner/gitlab4j-api/commit/9a20b8de5f18e20) gmessner *2014-02-20 07:42:44*

**Updated plugin versions.**


[224f552931fc7cb](https://github.com/gmessner/gitlab4j-api/commit/224f552931fc7cb) gmessner *2014-02-20 07:22:27*

**Added version to maven-compiler-plugin.**


[7311907ce4eb294](https://github.com/gmessner/gitlab4j-api/commit/7311907ce4eb294) gmessner *2014-02-20 07:19:03*

**Reset version number to 1.0.0-SNAPSHOT.**


[72569085dd55b16](https://github.com/gmessner/gitlab4j-api/commit/72569085dd55b16) gmessner *2014-02-20 07:16:23*

**Removed classes that are now in the gitlab-webhook package.**


[ebb6607b75ba015](https://github.com/gmessner/gitlab4j-api/commit/ebb6607b75ba015) gmessner *2014-02-20 05:37:35*

**Mods to support the sonatype.org OSS repository.**


[ac38a284e11b0ba](https://github.com/gmessner/gitlab4j-api/commit/ac38a284e11b0ba) gmessner *2014-02-20 05:35:53*

**Updated version.**


[8a76c8d74cb3d62](https://github.com/gmessner/gitlab4j-api/commit/8a76c8d74cb3d62) gmessner *2014-02-19 07:12:20*

**Mods for renamed classes.**


[5aad2fa287c7641](https://github.com/gmessner/gitlab4j-api/commit/5aad2fa287c7641) gmessner *2014-02-19 07:06:56*

**Moved from WebHook.**


[4608a8d9a693e28](https://github.com/gmessner/gitlab4j-api/commit/4608a8d9a693e28) gmessner *2014-02-19 07:06:36*

**Initial check-in.**


[1125e97b6e622b0](https://github.com/gmessner/gitlab4j-api/commit/1125e97b6e622b0) gmessner *2014-02-19 07:06:18*

**Renamed to WebHookResource.**


[dcdd01bd0834166](https://github.com/gmessner/gitlab4j-api/commit/dcdd01bd0834166) gmessner *2014-02-19 07:05:50*

**Moved from event package.**


[4ccb4cc55ad5f26](https://github.com/gmessner/gitlab4j-api/commit/4ccb4cc55ad5f26) gmessner *2014-02-19 07:05:06*

**Moved from event package.**


[da18e9a9eba37b9](https://github.com/gmessner/gitlab4j-api/commit/da18e9a9eba37b9) gmessner *2014-02-19 07:04:30*

**Cleaned up some comments.**


[05be8981760d6e7](https://github.com/gmessner/gitlab4j-api/commit/05be8981760d6e7) gmessner *2014-02-18 08:37:52*


## gitlab-api-1.0
### No issue

**Reset version to SNAPSHOT.**


[8992a17c95e3d01](https://github.com/gmessner/gitlab4j-api/commit/8992a17c95e3d01) gmessner *2014-02-18 08:27:55*

**Changed scm settings.**


[7cad8c73ecf45f0](https://github.com/gmessner/gitlab4j-api/commit/7cad8c73ecf45f0) gmessner *2014-02-18 08:24:40*

**Changes for performing releases.**


[012139a8eb5f50f](https://github.com/gmessner/gitlab4j-api/commit/012139a8eb5f50f) unknown *2014-02-18 08:16:41*

**Changed method names so that they are prefixed with "on".**


[7d705afcbd030ae](https://github.com/gmessner/gitlab4j-api/commit/7d705afcbd030ae) gmessner *2014-02-18 07:29:58*

**Added javax.servlet dependency to support the WebHook.**


[be42ba11382a56a](https://github.com/gmessner/gitlab4j-api/commit/be42ba11382a56a) gmessner *2014-02-18 07:29:18*

**Initial check-in.**


[8c85a1624c4cdae](https://github.com/gmessner/gitlab4j-api/commit/8c85a1624c4cdae) gmessner *2014-02-18 07:28:35*



