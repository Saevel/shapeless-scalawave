package prv.saevel.shapeleess.scalawave

class Conversion[SourceForm, IntermediaryForm, TargetForm,
                  InputFormat <: Format[IntermediaryForm, SourceForm],
                  OutputFormat <: Format[IntermediaryForm, TargetForm]](implicit sourceFormat: InputFormat,
                                                                        targetFormat: OutputFormat) {
  def convert(sourceData: SourceForm): TargetForm = targetFormat.serialize(sourceFormat.deserialize(sourceData)
}
