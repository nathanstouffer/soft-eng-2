!create plyr:Player
!create neutral:Neutral
!create panic:Panic
!create attack:Attack
!create die:Die

!insert (plyr, neutral) into player_playerState
!insert (plyr, panic) into player_playerState
!insert (plyr, attack) into player_playerState
!insert (plyr, die) into player_playerState

!set plyr.playerState := neutral
!set plyr.neutralState := neutral
!set plyr.panicState := panic
!set plyr.attackState := attack
!set plyr.dieState := die
