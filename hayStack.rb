require 'HTTParty'

class Haystack

  def get_collection
    stack = HTTParty.post( "http://challenge.code2040.org/api/haystack", 
                          :body => { "token"=> "8EBDqKCWgK"}.to_json, 
                          :headers => { 'Content-Type' => 'application/json' } )

    stack["result"]
  end

  def find_needle
    collection = get_collection
    print(collection)
    haystack = collection["haystack"]
    needle = collection["needle"]
    haystack.index(needle)
  end

  def validate_needle
    result = HTTParty.post( "http://challenge.code2040.org/api/validateneedle",
                                   :body => { "token" => "8EBDqKCWgK", "needle" => find_needle }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    print(result)
  end

  def print(someThing)
    puts someThing
  end
end

haystack = Haystack.new
haystack.validate_needle
