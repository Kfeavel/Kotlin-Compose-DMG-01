package gameboy.cpu

import gameboy.cpu.instructions.Instruction
import gameboy.cpu.registers.Registers
import gameboy.memory.MemoryBus

class CPU {
    private val registers = Registers()
    private val bus = MemoryBus()

    private fun execute(instruction: Instruction) {
        instruction.execute()
    }

    private fun step() {
        var opcode = bus.readByte(registers.pc)

        val prefixed = (opcode.compareTo(0xCBu) == 0)
        if (prefixed) {
            opcode = bus.readByte((registers.pc.plus(1u)).toUShort())
        }

        val instruction = Instruction.fromByte(opcode, prefixed, registers)
        execute(instruction)
    }
}
