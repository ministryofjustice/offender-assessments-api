-- This procedure is used to point an oasys environment at the appropriate matching
-- New Tech Sentence Plan application.

    create or replace procedure setSentencePlanWebappUrl( url in VARCHAR2 ) is
    cat_count number;
    ref_count number;
begin
    select count(*) into cat_count
    from EOR.REF_CATEGORY
    where REF_CATEGORY_CODE = 'SENTENCE_PLAN' and REF_CATEGORY_UK = -666;
    if (cat_count = 0)
    then
        insert into EOR.REF_CATEGORY (REF_CATEGORY_UK, REF_CATEGORY_CODE, CAT_CODE_DESC)
        values (-666, 'SENTENCE_PLAN', 'New Sentence Plan App');
    end if;

    select count(*) into ref_count
    from EOR.REF_ELEMENT
    where REF_CATEGORY_CODE = 'SENTENCE_PLAN';
    if (ref_count = 0)
    then
        insert into EOR.REF_ELEMENT (REF_ELEMENT_UK, REF_CATEGORY_CODE, REF_ELEMENT_CODE, START_DATE, REF_ELEMENT_DESC)
        values (-6666, 'SENTENCE_PLAN', 'SENTENCE_PLAN_WEBAPP_URL', sysdate, url);
    else
        update EOR.REF_ELEMENT set START_DATE = sysdate, REF_ELEMENT_DESC = url where REF_ELEMENT_UK = -6666;
    end if;

    commit;
end;

-- call setSentencePlanWebappUrl('http://www.bing.com');