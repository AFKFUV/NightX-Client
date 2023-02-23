package net.aspw.client.features.module.modules.movement.speeds.blocksmc

import net.aspw.client.Client
import net.aspw.client.event.JumpEvent
import net.aspw.client.event.MotionEvent
import net.aspw.client.event.MoveEvent
import net.aspw.client.features.module.modules.movement.Speed
import net.aspw.client.features.module.modules.movement.speeds.SpeedMode
import net.aspw.client.features.module.modules.world.Scaffold
import net.aspw.client.utils.MovementUtils.*
import kotlin.math.max

class BlocksMC : SpeedMode("BlocksMC") {

    override fun onJump(event: JumpEvent) {
        if (mc.thePlayer != null && isMoving())
            mc.timer.timerSpeed = 1.06f
        event.cancelEvent()
    }

    override fun onUpdate() {}

    override fun onMotion() {}

    override fun onMotion(event: MotionEvent) {
        val thePlayer = mc.thePlayer ?: return

        val speedModule = Client.moduleManager.getModule(Speed::class.java)!!
        val scaffoldModule = Client.moduleManager.getModule(Scaffold::class.java)

        if (isMoving()) {
            when {
                thePlayer.onGround && thePlayer.isCollidedVertically -> {
                    thePlayer.motionY = getJumpBoostModifier(
                        if (scaffoldModule!!.state) 0.41999 else 0.41999998688698, true
                    )

                    if (scaffoldModule.state) {
                        strafe(0.37F)
                    } else {
                        strafe(
                            (max(
                                0.48 + getSpeedEffect() * 0.1,
                                getBaseMoveSpeed(0.2873)
                            )).toFloat()
                        )
                    }
                }

                else -> {
                    setMotion(getSpeed().toDouble(), speedModule.strafing.get())
                }
            }
        }
    }

    override fun onDisable() {
        val scaffoldModule = Client.moduleManager.getModule(Scaffold::class.java)

        if (!mc.thePlayer.isSneaking && !scaffoldModule!!.state)
            strafe(0.2f)
    }

    override fun onMove(event: MoveEvent) {
        val thePlayer = mc.thePlayer ?: return
        val speedModule = Client.moduleManager.getModule(Speed::class.java)!!

        if (isMoving()) {
            when {
                thePlayer.isCollidedHorizontally -> {
                    setMotion(event, getBaseMoveSpeed(0.258), 1.0, speedModule.strafing.get())
                }
            }
        }
    }
}