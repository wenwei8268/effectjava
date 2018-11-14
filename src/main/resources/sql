---查询是否有transaction
SELECT COUNT(*)
      FROM v$transaction t, v$session s, v$mystat m
     WHERE t.ses_addr = s.saddr
       AND s.sid = m.sid
       AND ROWNUM = 1;
-- 查看各Schema所占空间
select
  owner,
  sum(bytes)/1024/1024/1024 schema_size_gig
from
  sys.dba_segments
group by
  owner;

  -- 查看各表占据大小
select segment_name, bytes/1024 size_KB
from user_segments
where segment_type='TABLE' and tablespace_name='USERS' order by size_KB desc


---
select t0.TEST_TYPE, t0.CATEGORY, t0.product_no,
       nvl(t23.amnt, 0) as dnatemp_qc, nvl(t18.amnt, 0) as techchecktmp_qc,
       nvl(t19.amnt, 0) as dkitem_ql, nvl(t20.amnt, 0) as techcheckitem_qc,
       nvl(t21.amnt, 0) as dnrmal_qc, nvl(t22.amnt, 0) as techcheckexcpt_qc,
       nvl(t1.amnt,0) as wktap_labprep, nvl(t2.amnt,0) as wktaskitem_labprep,
       nvl(t3.amnt,0) as poolingtasktemp_labprep, nvl(t4.amnt,0) as poolingitem_labprep, nvl(t5.amnt,0) as wkabnormal_labprep,
       nvl(t6.amnt,0) as poolingabnormal_labprep,
       nvl(t11.amnt,0) as wkblendtemp_onpooling, nvl(t12.amnt,0) as wkblenditem_onpooling, nvl(t9.amnt,0) as seqtemp_hiseq,
       nvl(t10.amnt,0) as seqitem_hiseq,
       nvl(t13.amnt,0) as seqtemp_novaseq, nvl(t14.amnt,0) as seqitem_novaseq,
       nvl(t9_0.amnt, 0)-nvl(t9.amnt,0)-nvl(t13.amnt,0) as seqtemp_otherseq,
       nvl(t10_0.amnt, 0)-nvl(t10.amnt,0)-nvl(t14.amnt,0) as seqitem_otherseq,
       nvl(t15_1.amnt,0) as wkblendabnormal_onpooling,
       nvl(t15_4.amnt, 0) as hiseq_deseq_abnor,  nvl(t15_5.amnt, 0) as novaseq_deseq_abnor,
       nvl(t15_3.amnt, 0)-nvl(t15_4.amnt, 0)-nvl(t15_5.amnt, 0) as otherseq_deseq_abnor,
       nvl(t15_6.amnt, 0) as hiseq_seq_abnor, nvl(t15_7.amnt, 0) as novaseq_seq_abnor,
       nvl(t15_2.amnt,0)-nvl(t15_6.amnt, 0)-nvl(t15_7.amnt, 0) as otherseq_seq_abnor,
       nvl(t16.amnt,0) as tranfertemp_upload, nvl(t17.amnt,0) as transferitem_upload
from (
     select t0_1.TEST_TYPE, t0_1.CATEGORY, t0_2.product_no from STAT_TEST_TYPE_CATEGORY t0_1
     cross join (select rownum as product_no from STAT_TEST_TYPE_CATEGORY where rownum < 4) t0_2
) t0 left join (
     select product_name, SAMPLE_STYLE, count(id) as amnt from wk_task_temp where state='1'
     ;



