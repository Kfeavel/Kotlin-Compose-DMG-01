package gameboy.cpu.instructions.arithmetic

import gameboy.cpu.registers.R8
import gameboy.cpu.registers.Registers
import kotlin.test.Test
import kotlin.test.assertEquals

class TestADDar8 {
    @Test
    fun `Test Add`() {
        val registers = Registers(
            a = 0xA0u,
            b = 0x0Bu
        ).apply {
            ADDar8(registers = this, dest = R8.B).execute()
        }
        assertEquals(0xABu, registers.a)
    }

    @Test
    fun `Test Zero`() {
        val registers = Registers(
            a = 0x00u,
            b = 0x00u
        ).apply {
            ADDar8(registers = this, dest = R8.B).execute()
        }
        assertEquals(true, registers.f.zero)
    }

    @Test
    fun `Test Subtract Flag`() {
        val registers = Registers(
            a = 0x00u,
            b = 0x00u
        ).apply {
            ADDar8(registers = this, dest = R8.B).execute()
        }
        assertEquals(false, registers.f.subtract)
    }

    @Test
    fun `Test Half Carry`() {
        val registers = Registers(
            a = 0x0Fu,
            b = 0x0Fu
        ).apply {
            ADDar8(registers = this, dest = R8.B).execute()
        }
        assertEquals(true, registers.f.halfCarry)
    }

    @Test
    fun `Test Overflow`() {
        val registers = Registers(
            a = 0xFFu,
            c = 0x01u
        ).apply {
            ADDar8(registers = this, dest = R8.C).execute()
        }
        assertEquals(true, registers.f.carry)
    }
}
