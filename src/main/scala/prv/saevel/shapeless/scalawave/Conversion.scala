package prv.saevel.shapeless.scalawave

object Conversion {

  def conversion[DataType,
                 InputForm,
                 F1 <: Format.Aux[DataType, InputForm],
                 OutputForm,
                 F2 <: Format.Aux[DataType, OutputForm]](implicit f1: F1, f2: F2): (InputForm => OutputForm) =
    ((f1.deserialize _) andThen (f2.serialize _))
}
