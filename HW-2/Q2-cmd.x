-- This file contains commands to create a possible state
-- of the Royalty and Loyalty model

-- create necessary objects
!create cust:Customer
!create prog:LoyaltyProgram
!create partner:ProgramPartner
!create slevel:ServiceLevel
!create account:LoyaltyAccount
!create card:CustomerCard

!create serv:Service
!set serv.pointsEarned := 1000

!create earn:Earning
!set earn.points := 900
!create burn:Burning
!set burn.points := 10

-- insert assocations
!insert (prog, cust) into Membership
!insert (prog, partner) into lp_pp
!insert (prog, slevel) into lp_sl

!insert (cust, card) into c_cc

!insert (Membership1, slevel) into m_sl
!insert (Membership1, account) into m_la
!insert (Membership1, card) into m_cc

!insert (partner, serv) into pp_s

!insert (account, earn) into la_t
!insert (account, burn) into la_t

!insert (serv, slevel) into s_sl
!insert (serv, earn) into s_t
!insert (serv, burn) into s_t

!insert (card, earn) into cc_t
!insert (card, burn) into cc_t
